package com.jhoel.api_alumnos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/alumnos")
public class AlumnosController {

    @Autowired
    private AlumnosRepository alumnosRepository;

    @GetMapping
    public List<Alumnos> listarAlumnos() {
        return alumnosRepository.findAll();
    }

    @PostMapping
    public Alumnos crearAlumno(@RequestBody Alumnos alumno) {
        return alumnosRepository.save(alumno);
    }

    @GetMapping("/{id}")
    public Alumnos obtenerAlumno(@PathVariable Long id) {
        return alumnosRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Alumnos actualizarAlumno(@PathVariable Long id, @RequestBody Alumnos alumnoActualizado) {
        Optional<Alumnos> alumnoOptional = alumnosRepository.findById(id);
        if (alumnoOptional.isPresent()) {
            Alumnos alumno = alumnoOptional.get();
            alumno.setNombre(alumnoActualizado.getNombre());
            //alumno.setNota(alumnoActualizado.getNota());
            return alumnosRepository.save(alumno);
        } else {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public void eliminarAlumno(@PathVariable Long id) {
        alumnosRepository.deleteById(id);
    }
}
