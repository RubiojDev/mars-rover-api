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
    let roverImg = document.createElement("img");

    roverImg.src = "images/rover.png";
    roverImg.alt = "rover";
    roverImg.id = "rover";

    document.getElementById("container").appendChild(roverImg);

    return roverImg;
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
    element.style.gridColumnStart = posX + 1;
}

function moveRover(posY, posX) {
    placeObject(rover, posY, posX);
}

async function deleteObstacles() {
    let response = await fetch("/obstacle/delete", {
        method: "DELETE",
    });

    if (!response.ok) {
        console.log("ERROR");
        return null;
    }

    return await response.text();
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

async function sendCommands() {
    let response = await fetch("/rover/command", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({ commandList: commands }),
    });

    return await response.json();
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

function clearInputCommands() {
    commands.length = 0;
    document.getElementById("input-commands").value = "";
}

document.getElementById("clear-btn").addEventListener("click", () => {
    clearInputCommands();
});

document.getElementById("send-btn").addEventListener("click", async () => {
    //agregar verificacion de si el arr esta vacio
    const responseCommands = await sendCommands();
    let rover = responseCommands.roverDto;
    let isObstacleEncountered = responseCommands.obstacleEncountered;

    //mostrar algun mensaje de obstaculo encontrado
    moveRover(rover.posY, rover.posX);
    clearInputCommands();
});

document.getElementById("reset-btn").addEventListener("click", async () => {
    const responseDelete = await deleteObstacles();

    if (!responseDelete) {
        console.log("Fallo");
        return;
    }

    const rocks = document.querySelectorAll(".rocks");
    rocks.forEach((rock) => rock.remove());
});

document.getElementById("obstacle-btn").addEventListener("click", () => {
    showModal();
});

function hideModal() {
    document.getElementById("modal-overlay").style.display = "none";
}

function showModal() {
    document.getElementById("modal-overlay").style.display = "block";
}

async function addObstacle(obstaclePosition) {
    let response = await fetch("/obstacle/create", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(obstaclePosition),
    });

    return await response.json();
}

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
        console.log(responseObstacle);

        //envia datos al back y chequear respuesta
        const rock = createRock();
        placeObject(rock, y, x);

        hideModal(); // cerrar modal
    });
