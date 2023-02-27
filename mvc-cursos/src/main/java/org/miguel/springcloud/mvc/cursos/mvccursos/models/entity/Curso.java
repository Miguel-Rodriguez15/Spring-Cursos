package org.miguel.springcloud.mvc.cursos.mvccursos.models.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cursos")
public class Curso {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty
   private String nombre;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CursoUsuario> cursoUsuarios;

    public Curso() {
        cursoUsuarios = new ArrayList<>();
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

     public void addCursoUsuario(CursoUsuario cursoUsuario){
        cursoUsuarios.add(cursoUsuario);
     }
     public  void  removeCursoUsuario(CursoUsuario cursoUsuario){
        cursoUsuarios.remove(cursoUsuario);
     }
    public List<CursoUsuario> getCursoUsuarios() {
        return cursoUsuarios;
    }

    public void setCursoUsuarios(List<CursoUsuario> cursoUsuarios) {
        this.cursoUsuarios = cursoUsuarios;
    }
}
