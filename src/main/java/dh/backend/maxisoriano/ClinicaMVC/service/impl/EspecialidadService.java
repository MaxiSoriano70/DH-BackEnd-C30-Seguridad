package dh.backend.maxisoriano.ClinicaMVC.service.impl;

import dh.backend.maxisoriano.ClinicaMVC.entity.Especialidad;
import dh.backend.maxisoriano.ClinicaMVC.exception.ResourceNotFoundException;
import dh.backend.maxisoriano.ClinicaMVC.repository.IEspecialidadRepository;
import dh.backend.maxisoriano.ClinicaMVC.service.IEspecialidadService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class EspecialidadService implements IEspecialidadService {
    private IEspecialidadRepository especialidadRepository;

    public EspecialidadService(IEspecialidadRepository especialidadRepository) {
        this.especialidadRepository = especialidadRepository;
    }

    @Override
    public Especialidad registrarEspecialidad(Especialidad especialidad) {
        return this.especialidadRepository.save(especialidad);
    }

    @Override
    public Optional<Especialidad> buscarPorId(int id) throws ResourceNotFoundException {
        Optional<Especialidad> especialidadOptional =especialidadRepository.findById(id);
        if(especialidadOptional.isEmpty()){
            throw new ResourceNotFoundException("{\"message\": \"especialidad no encontrada\"}");
        }
        return especialidadOptional;
    }

    @Override
    public List<Especialidad> buscarTodos() {
        return especialidadRepository.findAll();
    }

    @Override
    public void actualizarEspecialidad(Especialidad especialidad) {

    }

    @Override
    public void eliminarEspecialidad(Integer id) throws ResourceNotFoundException {

    }
}
