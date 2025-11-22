import { commands } from "./state.js";

export async function fetchGetRover() {
    try {
        let response = await fetch("/rover", {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
            },
        });

        if (!response.ok) {
            const error = await response.json();
            return { data: null, error: error.message };
        }

        const data = await response.json();
        return { data, error: null };
    } catch (e) {
        return { data: null, error: e.message };
    }
}

export async function fetchGetObstacles() {
    try {
        let response = await fetch("/obstacle", {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
            },
        });

        if (!response.ok) {
            const error = await response.json();
            return { data: null, error: error.message };
        }

        const data = await response.json();
        return { data, error: null };
    } catch (e) {
        return { data: null, error: e.message };
    }
}

export async function fetchSendCommands() {
    try {
        let response = await fetch("/rover/command", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({ commandList: commands }),
        });

        if (!response.ok) {
            const error = await response.json();
            return { data: null, error: error.message };
        }

        const data = await response.json();
        return { data, error: null };
    } catch (e) {
        return { data: null, error: e.message };
    }
}

export async function fetchAddObstacle(obstaclePosition) {
    try {
        let response = await fetch("/obstacle/create", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(obstaclePosition),
        });

        if (!response.ok) {
            const error = await response.json();
            return { data: null, error: error.message };
        }

        const data = await response.json();
        return { data, error: null };
    } catch (e) {
        return { data: null, error: e.message };
    }
}

export async function fetchDeleteObstacles() {
    try {
        let response = await fetch("/obstacle/delete", {
            method: "DELETE",
        });

        if (!response.ok) {
            const error = await response.json();
            return { data: null, error: error.message };
        }

        const data = await response.text();
        return { data, error: null };
    } catch (e) {
        return { data: null, error: e.message };
    }
}

export async function fetchGetConfig() {
    try {
        let response = await fetch("/config", {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
            },
        });

        if (!response.ok) {
            const error = await response.json();
            return { data: null, error: error.message };
        }

        const data = await response.json();
        return { data, error: null };
    } catch (e) {
        return { data: null, error: e.message };
    }
}
