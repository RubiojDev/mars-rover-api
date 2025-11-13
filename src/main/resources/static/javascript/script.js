let rover;
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

    placeObject(rover, roverJson.y, roverJson.x); //falta manejar las direcciones

    const obstaclesJson = await getObstacles();
    obstaclesJson.forEach((obstacleJson) => {
        let rock = createRock();
        placeObject(rock, obstacleJson.y, obstacleJson.x);
    });
}

function createRover() {
    const rover = document.createElement("img");

    rover.src = "images/rover.png";
    rover.alt = "rover";
    rover.id = "rover";

    document.getElementById("container").appendChild(rover);

    return rover;
}

function createRock() {
    const rock = document.createElement("img");

    rock.src = "images/rocks.png";
    rock.className = "rocks";
    rock.alt = "rocks";

    document.getElementById("container").appendChild(rock);

    return rock;
}

function placeObject(element, y, x) {
    element.style.gridRowStart = y + 1;
    element.style.gridColumnStart = x + 1;
}

function moveRover(y, x) {
    placeObject(rover, y, x);
}

function removeAllRocks() {
    //hacer consulta al servidor y chequear respuesta antes de borrar
    const rocks = document.querySelectorAll(".rocks");
    rocks.forEach((rock) => rock.remove());
}

async function getRover() {
    let roverResponse = await fetch("/api/rover", {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
        },
    });

    return await roverResponse.json();
    //Manejar los errores
}

async function getObstacles() {
    let obstacleResponse = await fetch("/api/obstacle", {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
        },
    });

    return await obstacleResponse.json();
    //Manejar los errores
}

document.getElementById("avanzar-btn").addEventListener("click", () => {
    inputCommands.value = "M";
});
