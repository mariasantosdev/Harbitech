package br.com.harbitech.school.subcategory;

import br.com.harbitech.school.validation.ValidationUtil;
import br.com.harbitech.school.category.Category;

public class SubCategory {

    private Long id;
    private String name;
    private String codeUrl;
    private String description;
    private String studyGuide;
    private SubCategoryStatus status;
    private int orderVisualization;
    private Category category;
    private ValidationUtil validateUtil;

    public SubCategory(){
        this.validateUtil = new ValidationUtil();
    }


    Long getId() {
        return id;
    }

    String getName() {
        return name;
    }

    String getCodeUrl() {
        return codeUrl;
    }

    String getDescription() {
        return description;
    }

    String getStudyGuide() {
        return studyGuide;
    }

    TypeIndicationSubCategory getIndicationCategory() {
        return indicationCategory;
    }

    int getOrderVisualization() {
        return orderVisualization;
    }

    Category getCategory() {
        return category;
    }

}
