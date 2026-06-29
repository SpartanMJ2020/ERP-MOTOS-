/* ============================================================
   Universidad Politécnica de Francisco I. Madero
   Proyecto: ERP de Venta de Motos
   Archivo:  app.js  —  Utilidades globales y cliente REST
   ============================================================ */

const API_BASE = 'http://localhost:8081/api';

/* ── Permisos por rol ────────────────────────────────────── */
const Permisos = {
    MODULOS: {
        dashboard:  ['Administrador', 'Vendedor', 'Contador', 'Almacenista'],
        ventas:     ['Administrador', 'Vendedor'],
        clientes:   ['Administrador', 'Vendedor'],
        finanzas:   ['Administrador', 'Contador'],
        inventario: ['Administrador', 'Almacenista'],
        empleados:  ['Administrador'],
        proveedores:  ['Administrador']
    },

    rol() {
        return Sesion.obtener()?.rol ?? null;
    },

    puedeAcceder(modulo) {
        const rol = this.rol();
        if (!rol) return false;
        const permitidos = this.MODULOS[modulo];
        return permitidos ? permitidos.includes(rol) : false;
    },

    requerirModulo(modulo) {
        if (!this.puedeAcceder(modulo)) {
            mostrarAlerta('No tienes permiso para acceder a este módulo.', 'error');
            setTimeout(() => { window.location.href = 'dashboard.html'; }, 1500);
            return false;
        }
        return true;
    },

    aplicarSidebar() {
        document.querySelectorAll('.sidebar nav a[href]').forEach(a => {
            const href = a.getAttribute('href');
            if (!href || href === 'dashboard.html') return;
            const modulo = href.replace('.html', '');
            const li = a.closest('li');
            if (li && !this.puedeAcceder(modulo)) li.style.display = 'none';
        });
    },

    aplicarDashboard() {
        const mapa = {
            'ventas.html':     '.mod-ventas',
            'clientes.html':   '.mod-clientes',
            'finanzas.html':   '.mod-finanzas',
            'inventario.html': '.mod-inventario',
            'empleados.html':  '.mod-empleados',
            'proveedores.html':  '.mod-provedor'
        };
        Object.entries(mapa).forEach(([href, sel]) => {
            const modulo = href.replace('.html', '');
            const el = document.querySelector(sel);
            if (el && !this.puedeAcceder(modulo)) el.style.display = 'none';
        });

        const stats = {
            'stat-ventas':    'ventas',
            'stat-clientes':  'clientes',
            'stat-motos':     'inventario',
            'stat-empleados': 'empleados',
            'stat-ingresos':  'finanzas',
            'stat-proveedores':  'provedor'
        };
        Object.entries(stats).forEach(([id, modulo]) => {
            const card = document.getElementById(id)?.closest('.stat-card');
            if (card && !this.puedeAcceder(modulo)) card.style.display = 'none';
        });
    }
};

/* ── Llamadas REST ───────────────────────────────────────── */
const Api = {
    _headers() {
        const h = { 'Content-Type': 'application/json' };
        const u = Sesion.obtener();
        if (u?.token) h['Authorization'] = `Bearer ${u.token}`;
        return h;
    },

    _manejar401(res) {
        if (res.status === 401 && Sesion.obtener()?.token) Sesion.cerrar();
    },

    async get(ruta) {
        const res = await fetch(`${API_BASE}${ruta}`, { headers: this._headers() });
        this._manejar401(res);
        if (!res.ok) throw new Error(`GET ${ruta} → ${res.status}`);
        return res.json();
    },

    async post(ruta, datos) {
        const res = await fetch(`${API_BASE}${ruta}`, {
            method: 'POST',
            headers: this._headers(),
            body: JSON.stringify(datos)
        });
        const text = await res.text();
        this._manejar401(res);
        if (!res.ok) throw new Error(text || `POST ${ruta} → ${res.status}`);
        return text;
    },

    async postJson(ruta, datos) {
        const res = await fetch(`${API_BASE}${ruta}`, {
            method: 'POST',
            headers: this._headers(),
            body: JSON.stringify(datos)
        });
        this._manejar401(res);
        if (!res.ok) {
            const text = await res.text();
            throw new Error(text || `POST ${ruta} → ${res.status}`);
        }
        return res.json();
    },

    async put(ruta, datos) {
        const res = await fetch(`${API_BASE}${ruta}`, {
            method: 'PUT',
            headers: this._headers(),
            body: JSON.stringify(datos)
        });
        const text = await res.text();
        this._manejar401(res);
        if (!res.ok) throw new Error(text || `PUT ${ruta} → ${res.status}`);
        return text;
    },

    async delete(ruta) {
        const res = await fetch(`${API_BASE}${ruta}`, { headers: this._headers(), method: 'DELETE' });
        this._manejar401(res);
        if (!res.ok) throw new Error(`DELETE ${ruta} → ${res.status}`);
        return res.text();
    }
};

/* ── Sesión ──────────────────────────────────────────────── */
const Sesion = {
    guardar(empleado) {
        sessionStorage.setItem('erp_usuario', JSON.stringify(empleado));
    },
    obtener() {
        const d = sessionStorage.getItem('erp_usuario');
        return d ? JSON.parse(d) : null;
    },
    async cerrar() {
        const u = this.obtener();
        if (u?.token) {
            try {
                await fetch(`${API_BASE}/empleados/empleado/logout`, {
                    method: 'POST',
                    headers: { 'Authorization': `Bearer ${u.token}` }
                });
            } catch (_) { /* ignorar error de red al cerrar */ }
        }
        sessionStorage.removeItem('erp_usuario');
        window.location.href = 'index.html';
    },
    requerida() {
        const u = this.obtener();
        if (!u || !u.token) { window.location.href = 'index.html'; return null; }
        return u;
    }
};

/* ── Notificaciones ──────────────────────────────────────── */
function mostrarAlerta(mensaje, tipo = 'exito', contenedorId = 'alerta-container') {
    const c = document.getElementById(contenedorId);
    if (!c) return;
    const iconos = { exito: '✅', error: '❌', info: 'ℹ️', advertencia: '⚠️' };
    c.innerHTML = `<div class="alerta alerta-${tipo}">${iconos[tipo] || ''} ${mensaje}</div>`;
    setTimeout(() => c.innerHTML = '', 4000);
}

/* ── Modal helpers ───────────────────────────────────────── */
function abrirModal(id) {
    document.getElementById(id)?.classList.add('activo');
}

function cerrarModal(id) {
    document.getElementById(id)?.classList.remove('activo');
}

/* ── Formateo ────────────────────────────────────────────── */
function formatearMoneda(n) {
    return new Intl.NumberFormat('es-MX', { style: 'currency', currency: 'MXN' }).format(n ?? 0);
}

function formatearFecha(f) {
    if (!f) return '—';
    if (Array.isArray(f)) {
        const [y, m, d] = f;
        const fecha = new Date(y, m - 1, d);
        return fecha.toLocaleDateString('es-MX', { day:'2-digit', month:'short', year:'numeric' });
    }
    const d = new Date(f);
    return isNaN(d) ? String(f) : d.toLocaleDateString('es-MX', { day:'2-digit', month:'short', year:'numeric' });
}

/* ── Tabla helpers ───────────────────────────────────────── */
function filtrarTabla(inputId, tablaId) {
    const val = document.getElementById(inputId)?.value.toLowerCase() ?? '';
    const filas = document.querySelectorAll(`#${tablaId} tbody tr`);
    filas.forEach(f => {
        f.style.display = f.textContent.toLowerCase().includes(val) ? '' : 'none';
    });
}

/* ── Cargar usuario en topbar ────────────────────────────── */
function cargarTopbarUsuario() {
    const u = Sesion.obtener();
    if (!u) return;
    const el = document.getElementById('topbar-nombre');
    const av = document.getElementById('topbar-avatar');
    const rol = document.getElementById('topbar-rol');
    if (el) el.textContent = u.nombre ?? 'Usuario';
    if (av) av.textContent = (u.nombre ?? 'U')[0].toUpperCase();
    if (rol) rol.textContent = u.rol ?? '';
    Permisos.aplicarSidebar();
}

document.addEventListener('DOMContentLoaded', cargarTopbarUsuario);
