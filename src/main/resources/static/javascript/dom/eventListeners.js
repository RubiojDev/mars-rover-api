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
    document
        .getElementById("button-moveForward")
        .addEventListener("click", () => {
            setCommandInput("M");
        });

    document
        .getElementById("button-turnRight")
        .addEventListener("click", () => {
            setCommandInput("R");
        });

    document.getElementById("button-turnLeft").addEventListener("click", () => {
        setCommandInput("L");
    });

    document.getElementById("button-clear").addEventListener("click", () => {
        clearCommand();
    });

    document
        .getElementById("button-add-obstacle")
        .addEventListener("click", () => {
            showModal();
        });

    document.getElementById("button-cancel").addEventListener("click", () => {
        hideModal();
    });

    document
        .getElementById("button-send")
        .addEventListener("click", async () => {
            const responseCommands = await sendCommands();

            if (responseCommands.error) {
                showError(responseCommands.error);
                return;
            }

            const rover = responseCommands.data.roverDto;
            const isObstacleEncountered =
                responseCommands.data.obstacleEncountered;

            if (isObstacleEncountered) {
                showError("Obstacle Encoutered");
            }

            moveRover(rover.posY, rover.posX, rover.direction);
            clearCommand();
        });

    document
        .getElementById("button-reset")
        .addEventListener("click", async () => {
            const responseDelete = await deleteObstacles();

            if (responseDelete.error) {
                showError(responseDelete.error);
                return;
            }

            const rocks = document.querySelectorAll(".rocks");
            rocks.forEach((rock) => rock.remove());
        });

    document
        .getElementById("button-send-position")
        .addEventListener("click", async () => {
            const x = parseInt(document.getElementById("input-posX").value);
            const y = parseInt(document.getElementById("input-posY").value);

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
