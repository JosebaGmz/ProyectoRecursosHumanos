package jg.rh.controlador;
import jg.rh.excepcion.RecursoNoEncontradoExcepcion;
import jg.rh.modelo.Empleado;
import jg.rh.servicio.EmpleadoServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//http://localhost:8080/recursos-humanos
@RequestMapping("recursos-humanos")
@CrossOrigin(value= "http://localhost:3000")
public class EmpleadoControlador {

    private static final Logger logger = LoggerFactory.getLogger(EmpleadoControlador.class);

    @Autowired
    private EmpleadoServicio empleadoServicio;

    //http://localhost:8080/recursos-humanos/empleados
    @GetMapping("/empleados")
    public List<Empleado> obtenerEmpleados(){
        List<Empleado> empleados = this.empleadoServicio.listarEmpleados();
        logger.info("Empleados obtenidos");
        empleados.forEach((empleado -> logger.info(empleado.toString())));
        return empleados;
    }

    @PostMapping("/empleados")
    public Empleado agregarEmpleado(@RequestBody Empleado empleado){
        logger.info("Empleado a agregar: " + empleado);
        return this.empleadoServicio.agregarEmpleado(empleado);
    }

    @GetMapping("/empleados/{id}")
    public ResponseEntity<Empleado> obtenerEmpleadoPorId(@PathVariable int id){
        Empleado empleado = this.empleadoServicio.buscarEmpleadoPorId(id);

        if(empleado!= null){
            return ResponseEntity.ok(empleado);
        }else{
            throw new RecursoNoEncontradoExcepcion("No se encontro ningun empleado con el id -> " + id);
        }
    }

    @PutMapping("/empleados/{id}")
    public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable int id,@RequestBody Empleado empleadoRecibido){
        Empleado empleado = this.empleadoServicio.buscarEmpleadoPorId(id);
        if(empleado == null){
            throw new RecursoNoEncontradoExcepcion("No se encontro el empleado con id -> "+ id);
        }else{
            empleado.setNombre(empleadoRecibido.getNombre());
            empleado.setDepartamento(empleadoRecibido.getDepartamento());
            empleado.setSueldo(empleadoRecibido.getSueldo());
            this.empleadoServicio.agregarEmpleado(empleado);
        }
        return ResponseEntity.ok(empleado);
    }

    @DeleteMapping("/empleados/{id}")
    public ResponseEntity<Map<String,Boolean>> eliminarEmpleado(@PathVariable int id){
        Empleado empleado = this.empleadoServicio.buscarEmpleadoPorId(id);

        if(empleado == null){
            throw new RecursoNoEncontradoExcepcion("No se encontro el empleado con id -> "+ id);
        }else{
            this.empleadoServicio.eliminarEmpleadoPorId(empleado.getIdEmpleado());
        }
        Map<String,Boolean> respuesta = new HashMap<>();
        respuesta.put("Eliminado",Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}
