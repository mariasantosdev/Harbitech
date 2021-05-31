package br.com.harbitech.school.alternative;

import br.com.harbitech.school.question.Question;

public class Alternative {

   private Long id;
   private String explanationText;
   private int orderVisualization;
   private TypeIndicationAlternative indicationAlternative;
   private String justification;
   private Question question;

   public Alternative(Long id, String explanationText, int orderVisualization, TypeIndicationAlternative indicationAlternative,
                      String justification, Question question) {
      this.id = id;
      this.explanationText = explanationText;
      this.orderVisualization = orderVisualization;
      this.indicationAlternative = indicationAlternative;
      this.justification = justification;
      this.question = question;
   }

   Long getId() {
      return id;
   }

   String getExplanationText() {
      return explanationText;
   }

   int getOrderVisualization() {
      return orderVisualization;
   }

   TypeIndicationAlternative getIndicationAlternative() {
      return indicationAlternative;
   }

   String getJustification() {
      return justification;
   }

   Question getQuestion() {
      return question;
   }
}
