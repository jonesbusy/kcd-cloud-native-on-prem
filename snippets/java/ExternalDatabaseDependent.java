@KubernetesDependent
public class ExternalDatabaseDependent extends PerResourcePollingDependentResource<DatabaseResource, TodoApp>
        implements Creator<DatabaseResource, TodoApp> {

    private DatabaseService databaseService;

    public ExternalDatabaseDependent(DatabaseService databaseService) {
        super(DatabaseResource.class, Duration.ofHours(1));
        this.databaseService = databaseService;
    }
    
    @Override protected DatabaseResource desired(TodoApp primary, Context<TodoApp> context) {
        return new DatabaseResource(primary.getMetadata().getName());
    }
    
    @Override public DatabaseResource create(DatabaseResource resource, TodoApp primary, Context<TodoApp> context) {
        return databaseService.createDatabase(resource);
    }
    
    @Override public Set<DatabaseResource> fetchResources(TodoApp primary) {
        if (!databaseService.hasDatabase(primary.getMetadata().getName())) return Set.of();
        return Set.of(databaseService.getDatabaseResource(primary.getMetadata().getName()));
    }
}
