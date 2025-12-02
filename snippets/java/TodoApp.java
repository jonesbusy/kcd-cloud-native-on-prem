@Group(TodoApp.GROUP)
@Version(TodoApp.VERSION)
@Plural(TodoApp.PLURAL)
@Kind(TodoApp.KIND)
public class TodoApp 
    extends CustomResource<TodoAppSpec, TodoAppStatus>
    implements Namespaced {

    public static final String KIND = "TodoApp";
    public static final String LOWER_NAME = "todoapp";
    public static final String PLURAL = "todoapps";
    public static final String GROUP = "apps.sso.elca.ch";
    public static final String VERSION = "v1";

}
