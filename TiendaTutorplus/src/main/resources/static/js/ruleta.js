document.addEventListener("DOMContentLoaded", () => {
    const options = [
        "🎁 30 min gratis",
        "🔟 10% off",
        "📚 Sesión repaso",
        "🎲 Gira otra vez",
        "💸 20% 1er mes",
        "😅 Intenta otra vez",
        "🎉 Cupón #1",
        "🎉 Cupón #2"
    ];

    const canvas = document.getElementById("canvas");
    const spinBtn = document.getElementById("spin");
    const codigoPremioTexto = document.getElementById("codigoPremio"); // Elemento para mostrar código
    const ctx = canvas.getContext("2d");

    const outsideRadius = 140;
    const textRadius = 110;
    const insideRadius = 60;

    let startAngle = 0;
    let arc = Math.PI * 2 / options.length;
    let spinTimeout = null;
    let spinAngleStart = 0;
    let spinTime = 0;
    let spinTimeTotal = 0;

    function yaGiroHoy() {
        const hoy = new Date().toDateString();
        const ultimaFecha = localStorage.getItem("fechaGiro");
        return ultimaFecha === hoy;
    }

    function marcarGiroHoy() {
        const hoy = new Date().toDateString();
        localStorage.setItem("fechaGiro", hoy);
    }

    function generarCodigoPremio() {
        const letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        let codigo = "";
        for (let i = 0; i < 8; i++) {
            codigo += letras.charAt(Math.floor(Math.random() * letras.length));
        }
        return codigo;
    }

    function drawRouletteWheel() {
        ctx.clearRect(0, 0, canvas.width, canvas.height);

        ctx.strokeStyle = "white";
        ctx.lineWidth = 2;
        ctx.font = "bold 14px sans-serif";

        for (let i = 0; i < options.length; i++) {
            const angle = startAngle + i * arc;
            ctx.fillStyle = getColor(i, options.length);

            ctx.beginPath();
            ctx.arc(150, 150, outsideRadius, angle, angle + arc, false);
            ctx.arc(150, 150, insideRadius, angle + arc, angle, true);
            ctx.stroke();
            ctx.fill();

            ctx.save();
            ctx.fillStyle = "black";
            ctx.translate(
                    150 + Math.cos(angle + arc / 2) * textRadius,
                    150 + Math.sin(angle + arc / 2) * textRadius
                    );
            ctx.rotate(angle + arc / 2 + Math.PI / 2);
            ctx.fillText(options[i], -ctx.measureText(options[i]).width / 2, 0);
            ctx.restore();
        }

        // Indicador rojo 🔺
        ctx.fillStyle = "red";
        ctx.beginPath();
        ctx.moveTo(150, 5);
        ctx.lineTo(140, 25);
        ctx.lineTo(160, 25);
        ctx.fill();
    }

    function rotateWheel() {
        spinTime += 30;
        if (spinTime >= spinTimeTotal) {
            stopRotateWheel();
            return;
        }

        const spinAngle = spinAngleStart - easeOut(spinTime, 0, spinAngleStart, spinTimeTotal);
        startAngle += (spinAngle * Math.PI / 180);
        drawRouletteWheel();
        spinTimeout = setTimeout(rotateWheel, 30);
    }

    function stopRotateWheel() {
        clearTimeout(spinTimeout);
        const degrees = startAngle * 180 / Math.PI + 90;
        const arcd = arc * 180 / Math.PI;
        const index = Math.floor((360 - (degrees % 360)) / arcd);
        const selectedText = options[index];
        const codigo = generarCodigoPremio();

        // Borra el centro
        ctx.fillStyle = "white";
        ctx.beginPath();
        ctx.arc(150, 150, insideRadius, 0, 2 * Math.PI);
        ctx.fill();

        // Muestra el premio en el centro
        ctx.fillStyle = "black";
        ctx.font = "bold 16px sans-serif";
        ctx.fillText(selectedText, 150 - ctx.measureText(selectedText).width / 2, 145);

        // Muestra el código en el centro
        ctx.font = "12px sans-serif";
        ctx.fillText(`Código: ${codigo}`, 150 - ctx.measureText(`Código: ${codigo}`).width / 2, 165);

        // También puedes mostrarlo fuera del canvas si lo deseas
        if (codigoPremioTexto) {
            codigoPremioTexto.textContent = `Tu código: ${codigo}`;
        }
    }

    function easeOut(t, b, c, d) {
        const ts = (t /= d) * t;
        const tc = ts * t;
        return b + c * (tc + -3 * ts + 3 * t);
    }

    function getColor(i, max) {
        const phase = 0;
        const center = 128;
        const width = 127;
        const freq = Math.PI * 2 / max;

        const r = Math.sin(freq * i + 2 + phase) * width + center;
        const g = Math.sin(freq * i + 0 + phase) * width + center;
        const b = Math.sin(freq * i + 4 + phase) * width + center;

        return `rgb(${Math.round(r)},${Math.round(g)},${Math.round(b)})`;
    }

    function spin() {
        if (yaGiroHoy()) {
            alert("🎯 Ya has girado la ruleta hoy. ¡Vuelve mañana!");
            return;
        }

        marcarGiroHoy(); // marca como girado
        spinAngleStart = Math.random() * 10 + 10;
        spinTime = 0;
        spinTimeTotal = Math.random() * 3000 + 4000;
        rotateWheel();
    }

    drawRouletteWheel();
    spinBtn.addEventListener("click", spin);
});
