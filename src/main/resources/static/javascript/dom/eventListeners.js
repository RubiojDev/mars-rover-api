import {
    moveRover,
    hideModal,
    showModal,
    showError,
    createRock,
    placeObject,
    clearCommand,
    setCommandInput,
} from "./uiFunctions.js";

import { addObstacle, sendCommands, deleteObstacles } from "../logic.js";

export function initDOMEvents() {
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

        if (responseCommands.error) {
            //manejar error
            showError(responseCommands.error);
            return;
        }

        const rover = responseCommands.data.roverDto;
        const isObstacleEncountered = responseCommands.data.obstacleEncountered; //mostrar algun mensaje de obstaculo encontrado

        if (isObstacleEncountered) {
            showError("Obstacle Encoutered");
        }
        moveRover(rover.posY, rover.posX, rover.direction);
        clearCommand();
    });

    document.getElementById("reset-btn").addEventListener("click", async () => {
        const responseDelete = await deleteObstacles();

        if (responseDelete.error) {
            showError(responseDelete.error);
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
                showError("The coordinates must be positive numbers");
                return;
            }

            const obstacle = { posX: x, posY: y };

            const responseObstacle = await addObstacle(obstacle);
            if (responseObstacle.error) {
                showError(responseObstacle.error);
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
}
