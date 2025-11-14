let rover;
let commands = [];
const inputCommands = document.getElementById("input-commands");

window.onload = () => {
    const filas = 5;
    const columnas = 5;

    const mapa = Array.from({ length: filas }, () => Array(columnas).fill(0));
    /*mapa[2][2] = 1;
    mapa[1][2] = 2;*/

    renderMap(mapa);
};

async function renderMap(mapa) {
    const container = document.getElementById("container");
    container.innerHTML = ""; // limpiar antes de volver a renderizar

    const rows = mapa.length;
    const cols = mapa[0].length;

    // ajustar la cuadrícula según tamaño de matriz
    container.style.gridTemplateRows = `repeat(${rows}, 1fr)`;
    container.style.gridTemplateColumns = `repeat(${cols}, 1fr)`;

    // Crear las celdas vacías (solo para que se vea la cuadrícula)
    for (let i = 0; i < rows * cols; i++) {
        const cell = document.createElement("div");
        cell.classList.add("cell");
        container.appendChild(cell);
    }

    rover = createRover();
    const roverJson = await getRover();

    placeObject(rover, roverJson.posY, roverJson.posX); //falta manejar las direcciones

    const obstaclesJson = await getObstacles();
    obstaclesJson.forEach((obstacleJson) => {
        let rock = createRock();
        placeObject(rock, obstacleJson.posY, obstacleJson.posX);
    });
}

function createRover() {
    let r = document.createElement("img");

    r.src = "images/rover.png";
    r.alt = "rover";
    r.id = "rover";

    document.getElementById("container").appendChild(r);

    return r;
}

function createRock() {
    const rock = document.createElement("img");

    rock.src = "images/rocks.png";
    rock.className = "rocks";
    rock.alt = "rocks";

    document.getElementById("container").appendChild(rock);

    return rock;
}

function placeObject(element, posY, posX) {
    element.style.gridRowStart = posY + 1;
    element.style.gridColumnStart = posY + 1;
}

function moveRover(posY, posX) {
    placeObject(rover, posY, posX);
}

function removeAllRocks() {
    //hacer consulta al servidor y chequear respuesta antes de borrar
    const rocks = document.querySelectorAll(".rocks");
    rocks.forEach((rock) => rock.remove());
}

async function getRover() {
    let roverResponse = await fetch("/rover", {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
        },
    });

    return await roverResponse.json();
    //Manejar los errores
}

async function getObstacles() {
    let obstacleResponse = await fetch("/obstacle", {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
        },
    });

    return await obstacleResponse.json();
    //Manejar los errores
}

function addCommand(command) {
    if (commands.length <= 15) {
        commands.push(command);
        document.getElementById("input-commands").value = commands.join(", ");
    }
}

document.getElementById("moveForward-btn").addEventListener("click", () => {
    addCommand("M");
});

document.getElementById("turnRight-btn").addEventListener("click", () => {
    addCommand("R");
});

document.getElementById("turnLeft-btn").addEventListener("click", () => {
    addCommand("L");
});

document.getElementById("clear-btn").addEventListener("click", () => {
    commands.length = 0;
    document.getElementById("input-commands").value = "";
});
