import AgregarEmpleado from "./empleados/AgregarEmpleado";
import EditarEmpleado from "./empleados/EditarEmpleado";
import ListadoEmpleados from "./empleados/ListadoEmpleados";
import Navegacion from "./plantilla/Navegacion";
import { BrowserRouter , Route, Routes} from "react-router-dom";

function App() {
  return (
    <div>
      <BrowserRouter>
      <Navegacion/>
      <Routes>
        <Route exact path='/' element= {<ListadoEmpleados/>}/>
        <Route exact path='/agregar' element= {<AgregarEmpleado/>}/>
        <Route exact path='/editar/:id' element= {<EditarEmpleado/>}/>
      </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
