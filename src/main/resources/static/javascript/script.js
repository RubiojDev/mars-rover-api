let rover;

window.onload = () => {
    const filas = 5;
    const columnas = 5;

    const mapa = Array.from({ length: filas }, () => Array(columnas).fill(0));
    mapa[2][2] = 1;
    mapa[1][2] = 2;

    renderMap(mapa);
};

function renderMap(mapa) {
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
    let rock = createRock();

    placeObject(rover, 2, 2);
    placeObject(rock, 1, 4);
    placeObject(createRock(), 2, 4);
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
    const rocks = document.querySelectorAll(".rocks");
    rocks.forEach((rock) => rock.remove());
}
