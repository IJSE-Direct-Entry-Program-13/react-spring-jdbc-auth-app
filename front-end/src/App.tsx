import './App.css'
import {createBrowserRouter, redirect, RouteObject, RouterProvider} from "react-router";
import Login from "./view/login/Login.tsx";

type Routes = RouteObject[];
const routes: Routes = [
    {
        index: true,
        loader: async ()=>{
            const response =
                await fetch('http://localhost:8080/app/clients', {credentials: 'include'});
            if (response.status === 200){
                //
            }else{
                return redirect('/login');
            }
        },
        element: <h1>Main</h1>
    },
    {
        path: 'login',
        Component: Login
    }
];
const router = createBrowserRouter(routes);

function App() {

  return <RouterProvider router={router}/>
}

export default App
