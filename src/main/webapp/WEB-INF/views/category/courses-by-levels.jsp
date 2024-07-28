<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${category.name} | Cursos online de tecnologia</title>
    <style>
        *, :after, :before {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        body {
            background-color: #f9fbfd;
        }
        body, input, select, textarea {
            font: normal 14px sans-serif;
        }
        a {
            outline: none;
        }
        ul {
            list-style: none;
        }
        .container {
            margin: 0 auto;
            max-width: 1200px;
            width: 96%;
        }
        .category-banner__wrapper {
            background-color: #a1b0c0;
        }
        .category-banner {
            display: flex;
            flex-direction: row;
            align-items: center;
            justify-content: flex-start;
        }
        .category-banner__icon img {
            max-height: 32px;
        }
        .category-banner__title {
            margin-left: 10px;
            display: inline-block;
            font-size: 2.4em;
            padding: 1em 0;
            letter-spacing: -1.2px;
            font-weight: 900;
            text-transform: uppercase;
            color: #fff;
        }
        .all-courses-finished-message {
            margin-block: 16px;
            font-size: 15px;
            color: green;
        }
        .subcategory__name {
            color: #6e767b;
            font-size: 1.313em;
            text-align: left;
            margin-top: 50px;
        }
        .courses__list {
            margin-top: 20px;
        }
        .course-card {
            display: flex;
            flex-direction: column;
            box-shadow: 4px 4px 20px 0 rgb(120 135 182 / 10%);
            background-color: #fff;
            border: 1px solid #eff1f9;
            border-radius: 4px;
            color: #747c81;
            transition: all.3s ease;
            padding: 15px;
            margin: 5px;
        }
        .course-card__name {
            font-weight: 600;
            opacity: .7;
            line-height: 1.29;
            font-size: 1em;
        }
        .course-card__hours {
            margin-top: 5px;
            font-size: 0.8750em;
        }

        @media (min-width: 540px) {
            .subcategories {
                padding: 15px 20px;
                border-radius: 4px;
                border: 1px solid #eff3fb;
            }
            .courses__list {
                display: flex;
                flex-wrap: wrap;
            }
            .course-card {
                width: calc(49% - 7.5px);
            }
        }
        @media (min-width: 940px) {
            .subcategories {
                display: flex;
                justify-content: flex-start;
                padding: 20px 20px 10px;
                margin-top: 20px;
            }
            .subcategories__label {
                margin-right: 10px;
                margin-bottom: 0;
            }
            .subcategories__list {
                display: flex;
                flex-wrap: wrap;
                list-style: none;
                margin-top: 5px;
            }
            .subcategories__item {
                margin: 0 10px 20px 0;
            }
            .course-card {
                width: calc(33.33333% - 10px);
            }
        }
        @media (min-width: 1024px) {
            .course-card {
                width: calc(25% - 15px);
            }
            .course-card__name {
                line-height: 1.36;
            }
        }
    </style>
</head>
<body>
<section class="category-banner__wrapper">
    <div class="container category-banner">
            <span class="category-banner__icon">
                <img src="${category.iconPath}">
            </span>
        <h1 class="category-banner__title">${category.name}</h1>
    </div>
</section>
<main class="container">
    <c:forEach items="${allActiveSubcategories}" var="subcategory" varStatus="status">
        <div class="subcategory" ${status.index != 0 ?  'hidden' :  ''}>
            <h2 id="${subcategory.codeUrl}" class="subcategory__name">${subcategory.name}</h2>
            <ul class="courses__list">
                <c:forEach items="${subcategory.getCourses()}" var="course">
                    <li class="course-card">
                        <h3 class="course-card__name">${course.name}</h3>
                        <p class="course-card__hours">${course.completionTimeInHours}h</p>
                        <c:choose>
                            <c:when test="${fn:contains(enrolledCourseIds, course.id)}">
                                <p class="all-courses-finished-message">Curso finalizado!</p>
                            </c:when>
                            <c:otherwise>
                                <button class="course-card__finish-course"
                                        style="margin-block: 16px;background-color: #747c81;border:
                                                    none;color: white;padding: 4px 6px;text-align: center;display: inline-block;
                                                    font-size: 16px;cursor: pointer;"
                                        data-course-code="${course.codeUrl}"
                                        onclick="postRequest(this)">Finalizar curso
                                </button>
                            </c:otherwise>
                        </c:choose>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </c:forEach>
    <button class="load-next-steps"
            style="margin-top: 20px;background-color: #4CAF50;border: none;color: white;padding: 10px 20px;text-align:
            center;display: ${allCoursesCompleted ? "block" : "none"};font-size: 16px;cursor: pointer;">Carregar próximos passos da jornada
    </button>

    <a href="https://discord.com/channels/1255292852024381513/1255293613361987664" class="discord-link">
        Link para a comunidade do discord
    </a>
</main>
</body>
<script>
    function postRequest(element) {
        const courseCode = element.getAttribute('data-course-code');
        const url = `/courses/` + courseCode + `/enroll`;

        fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
        })
            .then(response => {
                if (response.ok) {
                    console.log('Sucesso:', response.status);
                    const parentElement = element.parentElement;
                    parentElement.removeChild(element);
                    const message = document.createElement('p');
                    message.textContent = 'Curso finalizado!';
                    message.classList.add('all-courses-finished-message');
                    parentElement.appendChild(message);
                } else {
                    console.error('Erro:', response.status);
                }
            })
            .catch((error) => {
                console.error('Erro:', error);
            });

        return false;
    }

</script>
</html>