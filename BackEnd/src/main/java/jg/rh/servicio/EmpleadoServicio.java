package jg.rh.servicio;

import jg.rh.modelo.Empleado;
import jg.rh.repositorio.EmpleadoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmpleadoServicio implements IEmpleadoServicio{

    @Autowired
    private EmpleadoRepositorio empleadoRepositorio;
    @Override
    public List<Empleado> listarEmpleados() {
        return this.empleadoRepositorio.findAll();
    }

    @Override
    public Empleado buscarEmpleadoPorId(Integer idEmpleado) {
        return this.empleadoRepositorio.findById(idEmpleado).orElse(null);
    }

    @Override
    public Empleado agregarEmpleado(Empleado empleado) {
        return this.empleadoRepositorio.save(empleado);
    }

    @Override
    public void eliminarEmpleadoPorId(Integer idEmpleado) {
        this.empleadoRepositorio.deleteById(idEmpleado);
    }
}
