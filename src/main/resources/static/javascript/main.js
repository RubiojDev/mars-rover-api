import { getConfig } from "./logic.js";
import { renderMap } from "./dom/uiFunctions.js";
import { initDOMEvents } from "./dom/eventListeners.js";

document.addEventListener("DOMContentLoaded", init);

async function init() {
    const mapConfig = await getConfig();
    const rows = mapConfig.data.height;
    const cols = mapConfig.data.width;

    // renderiza el mapa
    await renderMap(rows, cols);

    // inicializa los event listeners (que ahora los movemos a DOM init)
    initDOMEvents();
}
