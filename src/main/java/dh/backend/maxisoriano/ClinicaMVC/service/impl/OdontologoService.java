package dh.backend.maxisoriano.ClinicaMVC.service.impl;

import dh.backend.maxisoriano.ClinicaMVC.entity.Especialidad;
import dh.backend.maxisoriano.ClinicaMVC.entity.Odontologo;
import dh.backend.maxisoriano.ClinicaMVC.exception.ResourceNotFoundException;
import dh.backend.maxisoriano.ClinicaMVC.repository.IOdontologoRepository;
import dh.backend.maxisoriano.ClinicaMVC.service.IEspecialidadService;
import dh.backend.maxisoriano.ClinicaMVC.service.IOdontologoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService implements IOdontologoService {
    private IOdontologoRepository odontologoRepository;
    private IEspecialidadService especialidadService;

    public OdontologoService(IOdontologoRepository odontologoRepository, IEspecialidadService especialidadService) {
        this.odontologoRepository = odontologoRepository;
        this.especialidadService = especialidadService;
    }

    @Override
    public Odontologo registrarOdontologo(Odontologo odontologo) {
        return this.odontologoRepository.save(odontologo);
    }

    @Override
    public Optional<Odontologo> buscarPorId(int id) {
        return this.odontologoRepository.findById(id);
    }

    @Override
    public List<Odontologo> buscarTodos() {
        return this.odontologoRepository.findAll();
    }

    @Override
    public void actualizarOdontologo(Odontologo odontologo) {
        this.odontologoRepository.save(odontologo);
    }

    @Override
    public void eliminarOdontologo(Integer id) throws ResourceNotFoundException {
        Optional<Odontologo> odontologoOptional = this.buscarPorId(id);
        if (odontologoOptional.isPresent()) {
            this.odontologoRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("{\"message\": \"odontologo no encontrado\"}");
        }

    }

    @Override
    public List<Odontologo> buscarPorApellido(String apellido) {
        return odontologoRepository.buscarPorApellido(apellido);
    }

    @Override
    public List<Odontologo> buscarPorNombre(String nombre) {
        return odontologoRepository.findByNombreLike(nombre);
    }

    @Override
    public Odontologo agregarEspecialidad(Integer id_odontologo, Integer id_especialidad) throws ResourceNotFoundException {
        Optional<Odontologo> odontologoOptional = this.buscarPorId(id_odontologo);
        if(odontologoOptional.isEmpty()){
            throw new ResourceNotFoundException("Odoltologo no encontrado");
        }
        Optional<Especialidad> especialidadOptional = this.especialidadService.buscarPorId(id_especialidad);
        if(especialidadOptional.isEmpty()){
            throw new ResourceNotFoundException("Especialidad no encontrada");
        }
        Odontologo odontologo = odontologoOptional.get();
        odontologo.getEspecialidades().add(especialidadOptional.get());
        this.actualizarOdontologo(odontologo);
        return odontologo;
    }
}
