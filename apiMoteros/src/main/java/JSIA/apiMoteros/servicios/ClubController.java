package JSIA.apiMoteros.servicios;

import JSIA.apiMoteros.daos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ClubController {

    @Autowired
    private ClubService clubService;

    // Endpoints para Club

    @GetMapping("/clubs")
    public List<Clubs> getAllClubs() {
        return clubService.getAllClubs();
    }

    @PostMapping("/clubs/login")
    public ResponseEntity<?> loginClub(@RequestBody LoginRequest loginRequest) {
        Clubs club = clubService.loginClub(loginRequest.getMail(), loginRequest.getContrasenya());
        if (club != null) {
            return ResponseEntity.ok(club);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Correo o contraseña incorrectos");
        }
    }

    @PostMapping("/clubs")
    public ResponseEntity<Clubs> createClub(@RequestBody Clubs club) {
        Clubs nuevoClub = clubService.createClub(club);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoClub);
    }

    @PutMapping("/clubs/{id}")
    public ResponseEntity<Clubs> updateClub(@PathVariable Long id, @RequestBody Clubs clubDetails) {
        try {
            return ResponseEntity.ok(clubService.updateClub(id, clubDetails));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/clubs/{id}")
    public ResponseEntity<Void> deleteClub(@PathVariable Long id) {
        clubService.deleteClub(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoints para Usuario

    @GetMapping("/usuarios")
    public List<Usuarios> getAllUsuarios() {
        return clubService.getAllUsuarios();
    }

    @PostMapping("/usuarios/login")
    public ResponseEntity<?> loginUsuario(@RequestBody LoginRequest loginRequest) {
        Usuarios usuario = clubService.loginUsuario(loginRequest.getMail(), loginRequest.getContrasenya());
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Correo o contraseña incorrectos");
        }
    }

    @PostMapping("/usuarios")
    public ResponseEntity<Usuarios> createUsuario(@RequestBody Usuarios usuario) {
        Usuarios nuevoUsuario = clubService.createUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
    }

    @PutMapping("/usuarios/{id}")
    public ResponseEntity<Usuarios> updateUsuario(@PathVariable Long id, @RequestBody Usuarios usuarioDetails) {
        try {
            return ResponseEntity.ok(clubService.updateUsuario(id, usuarioDetails));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        clubService.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }
}