package br.com.harbitech.school.subcategory;

import br.com.harbitech.school.category.Category;
import br.com.harbitech.school.course.Course;
import br.com.harbitech.school.course.CourseVisibility;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import javax.annotation.Nullable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static br.com.harbitech.school.validation.ValidationUtil.validateUrl;

@Entity
@NamedQuery(name = "Subcategory.allActive", query = "SELECT s FROM Subcategory s WHERE s.status = :status ORDER BY " +
        "s.orderVisualization")
@NamedQuery(name = "Subcategory.AllWithoutDescription", query = "SELECT s.name FROM Subcategory s WHERE s.description = '' " +
        "OR s.description IS NULL")
@Getter
@NoArgsConstructor(onConstructor = @__(@Deprecated))
public class Subcategory implements Comparable<Subcategory>, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String codeUrl;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(columnDefinition = "TEXT")
    private String studyGuide;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM")
    private SubCategoryStatus status = SubCategoryStatus.INACTIVE;
    private int orderVisualization = 0;
    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;
    @PositiveOrZero
    private int level;
    @OneToMany(mappedBy = "subcategory")
    @NotNull(message = "{subcategory.course.required}")
    private List<Course> courses = new ArrayList<>();
    @Nullable
    @ManyToOne
    @JoinColumn(name = "subcategory_base")
    private Subcategory subcategoryBase;

    public Subcategory(String name, String codeUrl, Category category, int level){
        Assert.hasText(name, "{subcategory.name.required}");
        Assert.hasText(codeUrl, "{subcategory.codeUrl.required}");
        Assert.notNull(category, "{subcategory.category.required}");
        validateUrl(codeUrl, "{subcategory.codeUrl.pattern}" + codeUrl);

        this.name = name;
        this.level = level;
        this.codeUrl = codeUrl;
        this.category = category;
        this.status = SubCategoryStatus.INACTIVE;
        this.orderVisualization = -1;
    }

    public Subcategory(String name, String codeUrl, int orderVisualization, String description, String studyGuide,
                       SubCategoryStatus status, Category category, int level, Subcategory subcategoryBase) {
        this(name, codeUrl, category, level);
        this.description = description;
        this.studyGuide = studyGuide;
        this.status = status;
        this.subcategoryBase = subcategoryBase;
        this.orderVisualization = orderVisualization;
    }

    public Subcategory(Long id,String name, String codeUrl, int orderVisualization, String description, String studyGuide,
                       SubCategoryStatus status, Category category, int level, Subcategory subcategoryBase){
        this(name,codeUrl,orderVisualization,description,studyGuide,status,category, level, subcategoryBase);
        this.id = id;
    }

    public String getStatusDescription(){
        return this.status.getDescription();
    }

    public String getCategoryCodeUrl(){
        return this.category.getCodeUrl();
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public Long getId() {
        return id;
    }

    @PositiveOrZero
    public int getLevel() {
        return level;
    }

    public void setOrderVisualization(int orderVisualization) {
        this.orderVisualization = orderVisualization;
    }

    public void setStudyGuide(String studyGuide) {
        this.studyGuide = studyGuide;
    }

    @Override
    public String toString() {
        return "SubCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", codeUrl='" + codeUrl + '\'' +
                ", description='" + description + '\'' +
                ", studyGuide='" + studyGuide + '\'' +
                ", status=" + status +
                ", orderVisualization=" + orderVisualization +
                ", category=" + category +
                '}';
    }

    @Override
    public int compareTo(Subcategory otherSubCategory) {
        if (otherSubCategory.getOrderVisualization() < this.orderVisualization) {
            return otherSubCategory.getOrderVisualization();
        }
        return this.orderVisualization;
    }

    public List<Course> getActiveCourses() {
        return this.courses.stream().filter(c -> c.getVisibility().equals(CourseVisibility.PUBLIC)).distinct().toList();
    }

    public void addCourse(Course course) {
        this.courses.add(course);
    }

    public void  update(SubcategoryFormUpdate subcategoryFormUpdate) {
        this.name = subcategoryFormUpdate.getName();
        this.codeUrl = subcategoryFormUpdate.getCodeUrl();
        this.level = subcategoryFormUpdate.getLevel();
        this.description = subcategoryFormUpdate.getDescription();
        this.status = subcategoryFormUpdate.getStatus();
        this.orderVisualization = subcategoryFormUpdate.getOrderVisualization();
        this.studyGuide = subcategoryFormUpdate.getStudyGuide();
        this.category = subcategoryFormUpdate.getCategory();
        this.subcategoryBase = subcategoryFormUpdate.getSubcategoryBase();
    }
}