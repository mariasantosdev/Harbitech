package br.com.harbitech.school.course;

import br.com.harbitech.school.subcategory.Subcategory;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@NamedQuery(name = "Course.allWithPublicVisibility", query = "SELECT c FROM Course c WHERE c.visibility = :visibility")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Por favor insira o nome do curso")
    @Size(max = 70, message = "Ops! O nome do curso não pode ter mais do que 70 caracteres")
    private String name;
    @Size(max = 70, message = "Ops! O código do curso não pode ter mais do que 70 caracteres")
    @NotBlank(message = "Por favor insira o código do curso")
    @Pattern(regexp = "[-a-z]+", message = "O código da url do curso está incorreto (só aceita letras minúsculas e hífen)")
    private String codeUrl;
    @Min(value = 1L, message = "O tempo estimado não pode ser menor do que 1 hora")
    @Min(value = 20L, message = "O tempo estimado não pode ultrapassar 20 horas")
    @NotBlank(message = "Por favor insira o tempo estimado do curso")
    private int completionTimeInHours;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM")
    @NotNull
    private CourseVisibility visibility = CourseVisibility.PRIVATE;
    @Size(max = 250, message = "Ops! O público alvo do curso não pode ter mais do que 250 caracteres")
    private String targetAudience;
    @NotBlank(message = "Por favor insira o nome do instrutor do curso")
    @Size(max = 70, message = "Ops! O nome do intrutor do curso não pode ter mais do que 70 caracteres")
    private String instructor;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(columnDefinition = "TEXT")
    private String developedSkills;
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull(message = "O curso precisa ter uma subcategoria associada")
    private Subcategory subcategory;

    public Course(String name, String codeUrl, int completionTimeInHours, CourseVisibility visibility,
                  String targetAudience, String instructor, String description, String developedSkills, Subcategory subcategory) {
        this.name = name;
        this.codeUrl = codeUrl;
        this.completionTimeInHours = completionTimeInHours;
        this.visibility = visibility;
        this.targetAudience = targetAudience;
        this.instructor = instructor;
        this.description = description;
        this.developedSkills = developedSkills;
        this.subcategory = subcategory;
    }

    @Deprecated
    public Course(){}

    public Course(String name, String codeUrl, int completionTimeInHours, String instructor, Subcategory subcategory){
        this.name = name;
        this.codeUrl = codeUrl;
        this.completionTimeInHours = completionTimeInHours;
        this.instructor = instructor;
        this.visibility = CourseVisibility.PRIVATE;
        this.subcategory = subcategory;
    }

    public Long getId() {
        return id;
    }

    public String getCodeUrl() {
        return codeUrl;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public int getCompletionTimeInHours() {
        return completionTimeInHours;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDevelopedSkills() {
        return developedSkills;
    }

    public CourseVisibility getVisibility() {
        return visibility;
    }

    public String getInstructor() {
        return instructor;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", codeUrl='" + codeUrl + '\'' +
                ", completionTimeInHours=" + completionTimeInHours +
                ", visibility=" + visibility +
                ", targetAudience='" + targetAudience + '\'' +
                ", instructor='" + instructor + '\'' +
                ", description='" + description + '\'' +
                ", developedSkills='" + developedSkills + '\'' +
                ", subCategory=" + subcategory +
                '}';
    }
}
