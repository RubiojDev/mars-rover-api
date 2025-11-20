import { commands } from "./state.js";
import { fetchGetRover } from "./api.js";
import { fetchAddObstacle } from "./api.js";
import { fetchGetObstacles } from "./api.js";
import { fetchSendCommands } from "./api.js";
import { fetchDeleteObstacles } from "./api.js";

export function clearCommandsArray() {
    commands.length = 0;
}

export function addCommandArray(command) {
    if (commands.length <= 15) {
        commands.push(command);
        return true;
    }
    return false;
}

export function sendCommands() {
    if (commands.length === 0) {
        return { error: "Invalid Command" };
    }

    return fetchSendCommands();
}

export function getRover() {
    return fetchGetRover();
}

export function getObstacles() {
    return fetchGetObstacles();
}

export function addObstacle(obstaclePosition) {
    return fetchAddObstacle(obstaclePosition);
}

export function deleteObstacles() {
    return fetchDeleteObstacles();
}
