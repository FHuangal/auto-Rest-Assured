@PetsAll
Feature: Apis from PetStore
  Como usuario de PetStore
  Quiero obtener la lista de pets con estado "available"
  Para poder verificar los detalles de los registros

  @CP01_Pets @PetStore
  Scenario: Obtener todas las mascotas exitosamente
    Given el actor establece el endpoint  de petStore
    When el actor realiza una solicitud GET
    Then el codigo de respuesta debe ser 200

  @CP02_Pets @Post_Pet @PetStore
    Scenario Outline: Crear una mascota exitosamente
      Given el actor establece el endpoint  de petStore
      When el actor crea un pet con el "<pet_id>" "<category_id>" "<category_name>" "<pet_name>" "<photoUrls>" "<tag_id>" "<tag_name>" "<status>"
      Then el codigo de respuesta debe ser 200
      Examples:
        | pet_id | category_id | category_name | pet_name | photoUrls    | tag_id   | tag_name | status    |
        |   257  | 0           | dogs          | Pietro   | SinImagen    | 0        | Educado  | available |
        #| 182    | 0           | cats          | Draco   | SinImagen    | 0        | Alegre   | available |

  @CP03_Pets @Put_Pet @PetStore
    Scenario Outline: Actualizar datos de una mascota exitosamente
      Given el actor establece el endpoint  de petStore
      When el actor actualiza un Pet con los datos "<category_id>" "<category_name>" "<pet_name>" "<photoUrls>" "<tag_id>" "<tag_name>" "<status>"
      Then el codigo de respuesta debe ser 200
      Examples:
        | category_id | category_name | pet_name | photoUrls    | tag_id   | tag_name   | status    |
        |      1      | cats          | Draco    | SinPhoto     | 0        | Salchicha  | available |