/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author rpbp
 */
public class TareaAsignada {
    private String dni;
    private String name;
    private String tarea_id;
    private String descripcion;
    private String medico_id;
    private String especialidad;
    private String code;

    public TareaAsignada(String dni, String name, String tarea_id, String descripcion, String medico_id, String especialidad, String code) {
        this.dni = dni;
        this.name = name;
        this.tarea_id = tarea_id;
        this.descripcion = descripcion;
        this.medico_id = medico_id;
        this.especialidad = especialidad;
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public TareaAsignada() {
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTarea_id() {
        return tarea_id;
    }

    public void setTarea_id(String tarea_id) {
        this.tarea_id = tarea_id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMedico_id() {
        return medico_id;
    }

    public void setMedico_id(String medico_id) {
        this.medico_id = medico_id;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
    
    
}
