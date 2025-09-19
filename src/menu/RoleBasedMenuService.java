package menu;

import java.util.ArrayList;
import java.util.List;

import model.Role;

public class RoleBasedMenuService implements IMenuService {

    // --- Singleton ---
    private static RoleBasedMenuService INSTANCE;
    public static synchronized RoleBasedMenuService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RoleBasedMenuService();
        }
        return INSTANCE;
    }

    private RoleBasedMenuService() { }

    @Override
    public List<String> optionsFor(Role role) {
        List<String> options = new ArrayList<>();
        switch (role) {
            case GERENTE_REGIONAL:
                options.add("Aprobar traspasos entre sucursales del país");
                options.add("Consultar stock de sucursales nacionales");
                options.add("Ver pedidos pendientes de aprobación");
                options.add("Reportes y estadísticas nacionales");
                break;
            case GERENTE:
                options.add("Gestionar pedidos de la sucursal");
                options.add("Consultar inventario de la sucursal");
                options.add("Aprobar solicitudes de pedidos de la sucursal");
                options.add("Gestionar usuarios de la sucursal");
                options.add("Ver pedidos por estado");
                break;
            case JEFE:
                options.add("Consultar stock de depósitos a cargo");
                options.add("Generar pedido de productos");
                options.add("Notificaciones y alertas");
                options.add("Reportes de su área");
                break;
            case OPERARIO:
                options.add("Consultar stock por sector");
                options.add("Reportar faltantes / incidencias");
                options.add("Seguimiento de pedidos");
                break;
            default:
                options.add("Sin opciones para este rol.");
                break;
        }
        return options;
    }
}
