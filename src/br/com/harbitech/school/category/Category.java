package br.com.harbitech.school.category;

public class Category {

    private String name;
    private String codeUrl;
    private String description;
    private String studyGuide;
    private TypeIndicationCategory indicationCategory;
    private int orderVisualization;
    private String PathImageOfIcon;
    private String codeColorHtml;

    public Category(String name, String codeUrl, String description, String studyGuide,
                    TypeIndicationCategory indicationCategory, int orderVisualization,
                    String pathImageOfIcon, String codeColorHtml) {
        this.name = name;
        this.codeUrl = codeUrl;
        this.description = description;
        this.studyGuide = studyGuide;
        this.indicationCategory = indicationCategory;
        this.orderVisualization = orderVisualization;
        PathImageOfIcon = pathImageOfIcon;
        this.codeColorHtml = codeColorHtml;
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

    TypeIndicationCategory getIndicationCategory() {
        return indicationCategory;
    }

    int getOrderVisualization() {
        return orderVisualization;
    }

    public String getPathImageOfIcon() {
        return PathImageOfIcon;
    }

    String getCodeColorHtml() {
        return codeColorHtml;
    }

    public static void validateUrl(String codeUrl) {
        boolean validacao = codeUrl.matches("[a-z]*");
        if (!validacao) {
            throw new RuntimeException("Não validado");
        }
        System.out.println("Validado");
    }
}
