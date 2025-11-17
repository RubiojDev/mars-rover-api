import { commands } from "./state.js";
import { getRover } from "./logic.js";
import { addObstacle } from "./logic.js";
import { getObstacles } from "./logic.js";
import { sendCommands } from "./logic.js";
import { addCommandArray } from "./logic.js";
import { deleteObstacles } from "./logic.js";
import { clearCommandsArray } from "./logic.js";

let roverImg;

document.getElementById("moveForward-btn").addEventListener("click", () => {
    setCommandInput("M");
});

document.getElementById("turnRight-btn").addEventListener("click", () => {
    setCommandInput("R");
});

document.getElementById("turnLeft-btn").addEventListener("click", () => {
    setCommandInput("L");
});

document.getElementById("clear-btn").addEventListener("click", () => {
    clearCommand();
});

document.getElementById("obstacle-btn").addEventListener("click", () => {
    showModal();
});

document.getElementById("cancelBtn").addEventListener("click", () => {
    hideModal();
});

document.getElementById("send-btn").addEventListener("click", async () => {
    const responseCommands = await sendCommands();
    console.log("1");
    if (responseCommands.error) {
        //manejar error
        console.log("2");
        console.log(responseCommands.error);
        return;
    }
    console.log("3");
    const rover = responseCommands.data.roverDto;
    const isObstacleEncountered = responseCommands.data.obstacleEncountered; //mostrar algun mensaje de obstaculo encontrado

    moveRover(rover.posY, rover.posX);
    clearCommand();
});

document.getElementById("reset-btn").addEventListener("click", async () => {
    const responseDelete = await deleteObstacles();

    if (responseDelete.error) {
        //error
        return;
    }

    const rocks = document.querySelectorAll(".rocks");
    rocks.forEach((rock) => rock.remove());
});

document
    .getElementById("setPositionBtn")
    .addEventListener("click", async () => {
        const x = parseInt(document.getElementById("posX-txt").value);
        const y = parseInt(document.getElementById("posY-txt").value);

        if (x < 0 || y < 0 || isNaN(x) || isNaN(y)) {
            alert("Las coordenadas deben ser números positivos");
            return;
        }

        const obstacle = { posX: x, posY: y };

        const responseObstacle = await addObstacle(obstacle);
        if (responseObstacle.error) {
            console.log("34");
            console.log(responseObstacle.error);
            return;
        }

        const rock = createRock();
        placeObject(
            rock,
            responseObstacle.data.posY,
            responseObstacle.data.posX
        );

        hideModal();
    });

window.onload = () => {
    const rows = 5;
    const cols = 5;

    renderMap(rows, cols);
};

async function renderMap(rows, cols) {
    //reducir el metodo
    const container = document.getElementById("container");
    container.innerHTML = ""; // limpiar antes de volver a renderizar

    // ajustar la cuadrícula según tamaño del mapa
    container.style.gridTemplateRows = `repeat(${rows}, 1fr)`;
    container.style.gridTemplateColumns = `repeat(${cols}, 1fr)`;

    // Crear las celdas vacías (solo para que se vea la cuadrícula)
    for (let i = 0; i < rows * cols; i++) {
        const cell = document.createElement("div");
        cell.classList.add("cell");
        container.appendChild(cell);
    }

    roverImg = createRover();
    const responseRover = await getRover();
    if (responseRover.error) {
        //manejar el error
        return;
    }

    placeObject(roverImg, responseRover.data.posY, responseRover.data.posX); //falta manejar las direcciones

    const responseObstacles = await getObstacles();

    if (responseObstacles.error) {
        //manejar el error
        return;
    }

    responseObstacles.data.forEach((obstacle) => {
        let rockImg = createRock();
        placeObject(rockImg, obstacle.posY, obstacle.posX);
    });
}

function createRover() {
    let roverImg = document.createElement("img");

    roverImg.src = "images/rover.png";
    roverImg.alt = "rover";
    roverImg.id = "rover";

    document.getElementById("container").appendChild(roverImg);

    return roverImg;
}

function createRock() {
    const rockImg = document.createElement("img");

    rockImg.src = "images/rocks.png";
    rockImg.className = "rocks";
    rockImg.alt = "rocks";

    document.getElementById("container").appendChild(rockImg);

    return rockImg;
}

function placeObject(element, posY, posX) {
    element.style.gridRowStart = posY + 1;
    element.style.gridColumnStart = posX + 1;
}

function moveRover(posY, posX) {
    placeObject(rover, posY, posX);
}

function setCommandInput(command) {
    if (addCommandArray(command)) {
        document.getElementById("input-commands").value = commands.join(", ");
    }
}

function showModal() {
    document.getElementById("modal-overlay").style.display = "block";
}

function hideModal() {
    document.getElementById("modal-overlay").style.display = "none";
}

function clearCommand() {
    clearCommandsArray();
    document.getElementById("input-commands").value = "";
}
