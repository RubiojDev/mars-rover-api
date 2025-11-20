// Importar los módulos
import {
    fetchGetRover,
    fetchGetObstacles,
    fetchSendCommands,
    fetchAddObstacle,
    fetchDeleteObstacles,
} from "./api.js";

import {
    getRover,
    getObstacles,
    addObstacle,
    sendCommands,
    deleteObstacles,
} from "./logic.js";

import { commands } from "./state.js"; // Si estás usando un archivo de estado para manejar comandos (en caso de que lo tengas)

// Aquí importamos las funciones de dom.js también si es necesario
import "./dom.js"; // Se puede importar solo el archivo dom.js para que se ejecute cuando se carga main.js

// Puedes inicializar o coordinar otras cosas aquí si es necesario
