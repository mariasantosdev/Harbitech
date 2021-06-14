package br.com.harbitech.school.category;

import br.com.harbitech.school.course.Course;
import br.com.harbitech.school.subcategory.SubCategory;

import java.util.ArrayList;
import java.util.List;

import static br.com.harbitech.school.validation.ValidationUtil.validateNonBlankText;
import static br.com.harbitech.school.validation.ValidationUtil.validateUrl;

public class Category {

    private Long id;
    private String name;
    private String codeUrl;
    private String description;
    private String studyGuide;
    private CategoryStatus status;
    private int orderVisualization;
    private String iconPath;
    private String htmlHexColorCode;
    private List<SubCategory> subCategories = new ArrayList<>();
    private List<Course> courses = new ArrayList<>();

    public Category(String name, String codeUrl) {
        validateNonBlankText(name, "O nome da categoria não pode estar em branco.");
        validateNonBlankText(codeUrl, "O código da URL da categoria não pode estar em branco.");
        validateUrl(codeUrl, "O código da url da categoria está incorreto (só aceita letras minúsculas e hífen): " + codeUrl);

        this.name = name;
        this.codeUrl = codeUrl;
        this.status = CategoryStatus.INACTIVE;
        this.orderVisualization = -1;
    }


    public Category(String name) {
        this.name = name;
    }

    public Category(String name, String codeUrl, String description, String studyGuide, CategoryStatus status,
                    int orderVisualization, String iconPath, String htmlHexColorCode) {
        this.name = name;
        this.codeUrl = codeUrl;
        this.description = description;
        this.studyGuide = studyGuide;
        this.status = status;
        this.orderVisualization = orderVisualization;
        this.iconPath = iconPath;
        this.htmlHexColorCode = htmlHexColorCode;
    }

    public void setName(String name) {
        this.name = name;
    }

    Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCodeUrl() {
        return codeUrl;
    }

    public String getDescription() {
        return description;
    }

    String getStudyGuide() {
        return studyGuide;
    }

    public CategoryStatus getStatus() {
        return status;
    }

    public int getOrderVisualization() {
        return orderVisualization;
    }

    public String getIconPath() {
        return iconPath;
    }

    public String getHtmlHexColorCode() {
        return htmlHexColorCode;
    }

    public void setAllSubCategorys() {
        this.subCategories.forEach(s -> System.out.println(s.getName().toString()));
        this.subCategories.forEach(s -> System.out.println(s.getDescription().toString()));
//        this.courses.forEach(s -> System.out.println(s.getSubCategory());
    }

    public int totalCourses() {
        return this.courses.size();
    }

    public int totalTimeInHoursOfCourse() {
        return this.courses.stream().mapToInt(Course::getCompletionTimeInHours).sum();
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", codeUrl='" + codeUrl + '\'' +
                ", description='" + description + '\'' +
                ", studyGuide='" + studyGuide + '\'' +
                ", status=" + status +
                ", orderVisualization=" + orderVisualization +
                ", iconPath='" + iconPath + '\'' +
                ", htmlHexColorCode='" + htmlHexColorCode + '\'' +
                '}';
    }
}

