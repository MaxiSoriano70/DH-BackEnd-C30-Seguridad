package dh.backend.maxisoriano.ClinicaMVC.controller;

import dh.backend.maxisoriano.ClinicaMVC.entity.Especialidad;
import dh.backend.maxisoriano.ClinicaMVC.entity.Paciente;
import dh.backend.maxisoriano.ClinicaMVC.exception.ResourceNotFoundException;
import dh.backend.maxisoriano.ClinicaMVC.service.IEspecialidadService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/especialidad")
public class EspecialidadController {
    private IEspecialidadService especialidadService;
    public EspecialidadController(IEspecialidadService especialidadService) {
        this.especialidadService = especialidadService;
    }
    @PostMapping
    public ResponseEntity<Especialidad> registrarEspecialidad(@RequestBody Especialidad especialidad){
        return ResponseEntity.ok(especialidadService.registrarEspecialidad(especialidad));
    }
    @GetMapping
    public ResponseEntity<List<Especialidad>> buscarTodos(){
        return ResponseEntity.ok(especialidadService.buscarTodos());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Especialidad> buscarEspecialidadPorId(@PathVariable Integer id) throws ResourceNotFoundException {
        Optional<Especialidad> especialidad = especialidadService.buscarPorId(id);
        if(especialidad.isPresent()){
            Especialidad especialidadARetornar = especialidad.get();
            return ResponseEntity.ok(especialidadARetornar);
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @PutMapping
    public ResponseEntity<String> actualizarEspecialidad(@RequestBody Especialidad especialidad) throws ResourceNotFoundException {
        Optional<Especialidad> especialidadOptional = especialidadService.buscarPorId(especialidad.getId());
        if (especialidadOptional.isPresent()) {
            especialidadService.actualizarEspecialidad(especialidad);
            return ResponseEntity.ok("{\"message\": \"Especialidad modificada\"}");
        } else {
            return new ResponseEntity<>("{\"message\": \"Especialidad no encontrada\"}", HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrarEspecialidad(@PathVariable Integer id) throws ResourceNotFoundException {
        especialidadService.eliminarEspecialidad(id);
        return ResponseEntity.ok("{\"message\": \"Especialidad eliminada\"}");
    }
}
