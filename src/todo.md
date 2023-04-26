- add size for paging
- validations query params
- methods
- endpoint for adding
- https://www.baeldung.com/spring-rest-openapi-documentation
- ControllerAdvice

before lab 2
- rename ArticleNotFoundExceptionHandler to GeneralExceptionHandler
- improve method mapping according to https://yakworks.github.io/gorm-rest-api/design-principles/http-method-mapping/
- insert here if anything else appears

- lab 2

Все методы find можно заменить на find(Predicate filter)

Если это ArticleController с маппингом api/article, то имеет смысл перебросить всю логику со StudopediaController в ArticleController

Где-то используем ResponseEntity, где-то нет

Никакой валидации
