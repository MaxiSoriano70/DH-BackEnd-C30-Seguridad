package dh.backend.maxisoriano.ClinicaMVC.service;

import dh.backend.maxisoriano.ClinicaMVC.entity.Especialidad;
import dh.backend.maxisoriano.ClinicaMVC.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface IEspecialidadService {
    Especialidad registrarEspecialidad(Especialidad especialidad);
    Optional<Especialidad> buscarPorId(int id) throws ResourceNotFoundException;
    List<Especialidad> buscarTodos();
    void actualizarEspecialidad(Especialidad especialidad);
    void eliminarEspecialidad(Integer id) throws ResourceNotFoundException;
}
