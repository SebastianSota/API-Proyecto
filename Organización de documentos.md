Clases(pojo) UpperCamelCase AreaIngrediente 
atributos\ metodos LowerCamelCase  insertarIngrediente()
ConnectionDB //Personal 
DAO() AreaIngredienteDao
Servicio ServiceAreaIngrediente

//Bean	 
Persona(int id, String nombre){
}

//Pojo
//Si solo implemetas el constructor vacío es un POJO
Persona(){
}


## Nombramiento de los documentos Bean y Dao:

Ingrediente
AreaIngrediente
Platillo
UnidadMedida 
Preparacion
TipoPlatillo
TipoMenu
Sucursal
Pedido
Persona
Usuario
Promocion
Direccion
Rol
Contacto
Menu
MenuDia
Ponderacion
IngredientePlatillo
PlatilloMenu
ImagenPlatillo
PedidoTienePlatillo
PedidoTienePromocion
SucursalTieneUsuario
Dia
TipoDia
TipoContacto
PromocionTienePlatilloMenu
Precio

## En orden
[D]1.-AreaIngrediente 
[D]2.-Ingrediente
[D]3.-UnidadMedida
[D]4.-TipoPlatillo
[D]5.-Platillo
[D]6.-IngredientePlatillo
[S]7.-Preparacion
[S]8.-Precio
[S]9.-ImagenPlatillo
[S]10.-Promocion
[S]11.-PromocionTienePlatilloMenu
[S]12.-Sucursal
[E]13.-TipoMenu
[E]14.-Menu
[E]15.-MenuDia
[E]16.-PlatilloMenu
[E]17.-Dia
[E]18.-TipoDia
[N]19.-Persona
[N]20.-TipoContacto
[N]21.-Contacto
[N]22.-Rol
[N]23.-Usuario
[M]24.-SucursalTieneUsuario
[M]25.-Direccion
[M]26.-Pedido
[M]27.-PedidoTienePlatillo
[M]28.-PedidoTienePromocion
[M]29.-Ponderacion