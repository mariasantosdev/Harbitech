<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="templates" tagdir="/WEB-INF/tags/templates" %>

<templates:admin-template title="Nova Categoria">
<form:form class="form-horizontal" modelAttribute="subcategoryForm" method="post" action="${formAction}">
<div class="panel panel-default">
    <div class="panel-heading">
        <h1 class="panel-title">Nova Subcategoria</h1>
    </div>
                <div class="panel-body">
                    <div class="form-group">
                <label for="name" class="col-sm-2 control-label">Nome</label>
                <div class="col-sm-7">
                    <form:input type="text" class="form-control" id="name" name="name"
                                placeholder="Digite aqui o nome da subcategoria" path="name"/>
                    <form:errors path="name" cssClass="error"/>
                </div>
                     </div>
            <div class="form-group">
                <label for="codeUrl" class="col-sm-2 control-label">Código</label>
                <div class="col-sm-7">
                    <form:input type="text" class="form-control" id="codeUrl" name="codeUrl"
                                placeholder="por exemplo:java, python (não use letras maiúsculas, acentos ou caracteres especiais)"
                                path="codeUrl"/>
                    <form:errors path="codeUrl" cssClass="error"/>
                </div>
            </div>

            <div class="form-check">
                <label for="flexRadioDefault1" class="col-sm-2 control-label">Status</label>
                <div class="col-sm-2">
                <input class="form-check-input" type="radio" name="status" id="flexRadioDefault1"
                       value="ACTIVE" ${subcategoryForm.status == 'ACTIVE' ? "checked" : ""} />
                    ATIVA
            </div>
                <div>
                <input class="form-check-input" type="radio" name="status" id="flexRadioDefault2"
                       value="INACTIVE" ${subcategoryForm.status == 'INACTIVE' ? "checked" : ""} />
                INATIVA
                </div>
            <div class="form-check">
            </div>
            <br>
            <div class="form-group">
                <label for="orderVisualization" class="col-sm-2 control-label">Ordem da subcategoria</label>
                <div class="col-sm-7">
                    <form:input type="number" class="form-control" id="orderVisualization" name="orderVisualization"
                           placeholder="por exemplo: subcategorias de ordem 1 aparece antes da subcategoria de ordem 2" path="orderVisualization"/>
                    <form:errors path="orderVisualization" cssClass="error"/>
                </div>
            </div>
           <div class="form-group">
                <label for="level" class="col-sm-2 control-label">Nível</label>
                <div class="col-sm-7">
                        <form:input type="number" class="form-control" id="level" name="level"
                                    placeholder="por exemplo: conhecimentos essenciais são nível 0 já os mais avançados seriam níveis superiores" path="level"/>
                        <form:errors path="level" cssClass="error"/>
                </div>
           </div>

            <div class="form-group">
                <label class="col-sm-2 control-label" for="studyGuide">Guias de estudo</label>
                <div class="col-sm-7">
                <form:textarea class="form-control" id="studyGuide" name="studyGuide" rows="9"
                          placeholder="Um texto apontando para formações para ajudar pessoas perdidas" path="studyGuide"></form:textarea>
                    <form:errors path="studyGuide" cssClass="error"/>
                </div>
            </div>

            <div class="form-group">
                <label for="description" class="col-sm-2 control-label">Descrição</label>
                <div class="col-sm-7">
                    <form:input type="text" class="form-control" id="description" name="description"
                           placeholder="por exemplo: Laravel, CakePHP e Codelgniter são frameworks que você irá treinar bastante aqui." path="description"/>
                    <form:errors path="description" cssClass="error"/>
                </div>
            </div>
                <div class="form-group">
                    <label for="category" class="col-sm-2 control-label">Categoria</label>
                    <div class="col-sm-7">
                        <form:select path="category" class="form-control" id = "category">
                            <form:options items="${categories}" itemValue="id" itemLabel="name"/>
                        </form:select>
                    </div>
                </div>

            <form:input type="hidden" path="id"/>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-success">Enviar</button>
                </div>
            </div>
        </div>
    </div>
</form:form>
</templates:admin-template>