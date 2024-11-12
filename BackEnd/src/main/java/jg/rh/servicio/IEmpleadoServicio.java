package jg.rh.servicio;

import jg.rh.modelo.Empleado;

import java.util.List;

public interface IEmpleadoServicio {

    public List<Empleado> listarEmpleados();
    public Empleado buscarEmpleadoPorId(Integer idEmpleado);
    public Empleado agregarEmpleado(Empleado empleado);
    public void eliminarEmpleadoPorId(Integer idEmpleado);
}
