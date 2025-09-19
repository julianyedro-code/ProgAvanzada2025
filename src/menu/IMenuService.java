package menu;

import java.util.List;
import model.Role;

public interface IMenuService {
    List<String> optionsFor(Role role);
}
