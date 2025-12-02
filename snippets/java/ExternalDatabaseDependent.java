@KubernetesDependent
public class ExternalDatabaseDependent extends PerResourcePollingDependentResource<DatabaseResource, TodoApp>
        implements Creator<DatabaseResource, TodoApp> { // Traits

    private DatabaseService databaseService;

    public ExternalDatabaseDependent(DatabaseService databaseService) {
        super(DatabaseResource.class, Duration.ofHours(1)); // Polling interval
        this.databaseService = databaseService;
    }

    @Override
    protected DatabaseResource desired(TodoApp primary, Context<TodoApp> context) {
        return new DatabaseResource(primary.getSpec().group());
    }

    @Override
    public DatabaseResource create(DatabaseResource resource, TodoApp primary, Context<TodoApp> context) {
        return databaseService.createDatabase(resource);
    }

    @Override
    public Set<DatabaseResource> fetchResources(TodoApp primary) {
        if (!databaseService.hasDatabase(primary.getSpec().group())) return Set.of();
        return Set.of(databaseService.getDatabaseResource(primary.getSpec().group()));
    }
}
