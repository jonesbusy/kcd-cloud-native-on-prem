@ControllerConfiguration(
        dependents = {
            @Dependent(type = ExternalDatabaseDependent.class, reconcileCondition = IsProductionCondition.class),
            @Dependent(type = KubernetesDatabaseDependent.class, reconcileCondition = IsDevelopmentCondition.class),
            @Dependent(type = ConfigMapDependent.class, dependsOn = {ExternalDatabaseDependent.class, DatabaseDependent.class}),
        }
)
public class TodoAppReconciler implements Reconciler<TodoApp> {

    @Override
    public UpdateControl<TodoApp> reconcile(TodoApp resource, Context<TodoApp> context) {

        final var result = context.managedWorkflowAndDependentResourceContext()
                .getWorkflowReconcileResult()
                .orElseThrow()
                .allDependentResourcesReady();

        if (!result) {
            return UpdateControl.patchStatus(resource.withStatus("NotReady")).rescheduleAfter(5L, TimeUnit.SECONDS);
        }
        if (resource.getStatus().equals("Ready")) {
            return UpdateControl.noUpdate();
        }
        return UpdateControl.patchStatus(resource.withStatus("Ready"));

    }
}
