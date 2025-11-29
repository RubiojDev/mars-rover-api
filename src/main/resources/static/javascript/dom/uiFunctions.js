import { commands } from "../state.js";
import {
    getRover,
    getObstacles,
    addCommandArray,
    clearCommandsArray,
} from "../logic.js";

let roverImg;

const roverRotation = {
    EAST: "rotate(0deg) scaleX(1)",
    SOUTH: "rotate(90deg) scaleX(1)",
    WEST: "rotate(0deg) scaleX(-1)",
    NORTH: "rotate(270deg) scaleX(1)",
};

export async function renderMap(rows, cols) {
    const container = document.getElementById("container");
    container.innerHTML = ""; // limpiar antes de volver a renderizar

    renderGrid(container, rows, cols);

    const responseRover = await getRover();
    if (responseRover.error) {
        showError(responseRover.error);
        return;
    }

    roverImg = createRover(responseRover.data.direction);
    placeObject(roverImg, responseRover.data.posY, responseRover.data.posX);

    const responseObstacles = await getObstacles();
    if (responseObstacles.error) {
        showError(responseObstacles.error);
        return;
    }

    responseObstacles.data.forEach((obstacle) => {
        let rockImg = createRock();
        placeObject(rockImg, obstacle.posY, obstacle.posX);
    });

    showSuccess("Map Loaded");
}

export function createRock() {
    const rockImg = document.createElement("img");

    rockImg.src = "images/rocks.webp";
    rockImg.className = "rocks";
    rockImg.alt = "rocks";

    return rockImg;
}

export function placeObject(element, posY, posX) {
    document.getElementById("container").appendChild(element);
    element.style.gridRowStart = posY + 1;
    element.style.gridColumnStart = posX + 1;
    animationPlaceObject(element);
}

export function moveRover(posY, posX, direction) {
    roverImg.style.transform = roverRotation[direction];
    placeObject(roverImg, posY, posX);
}

export function setCommandInput(command) {
    if (addCommandArray(command)) {
        document.getElementById("input-commands").value = commands.join(", ");
    } else {
        showError("Max 15 commands allowed");
    }
}

export function showModal() {
    document.getElementById("modal-overlay").style.display = "block";
}

export function hideModal() {
    document.getElementById("modal-overlay").style.display = "none";
}

export function clearCommand() {
    clearCommandsArray();
    document.getElementById("input-commands").value = "";
}

export function showError(message) {
    const box = document.getElementById("message-box");
    box.textContent = message;

    box.classList.remove("message-success");
    box.classList.add("message-error");

    // mostrar
    box.style.top = "20px";

    // ocultar automáticamente
    setTimeout(() => {
        box.style.top = "-100%";
    }, 2500);
}

function renderGrid(container, rows, cols) {
    // ajustar la cuadrícula según tamaño del mapa
    container.style.gridTemplateRows = `repeat(${rows}, 1fr)`;
    container.style.gridTemplateColumns = `repeat(${cols}, 1fr)`;

    // Crear las celdas vacías (solo para que se vea la cuadrícula)
    for (let i = 0; i < rows * cols; i++) {
        const cell = document.createElement("div");
        cell.classList.add("cell");
        container.appendChild(cell);
    }
}

function createRover(direction) {
    let roverImg = document.createElement("img");

    roverImg.src = "images/rover.webp";
    roverImg.alt = "rover";
    roverImg.id = "rover";
    roverImg.style.transform = roverRotation[direction];

    return roverImg;
}

function animationPlaceObject(element) {
    element.animate(
        [
            { scale: 0.8, opacity: 0.5 },
            { scale: 1, opacity: 1 },
        ],
        {
            duration: 200,
            easing: "ease-out",
        }
    );
}

function showSuccess(message) {
    const box = document.getElementById("message-box");
    box.textContent = message;

    box.classList.remove("message-error");
    box.classList.add("message-success");

    // mostrar
    box.style.top = "20px";

    // ocultar automáticamente
    setTimeout(() => {
        box.style.top = "-100%";
    }, 2500);
}
