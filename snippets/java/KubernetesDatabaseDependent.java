@KubernetesDependent
public class KubernetesDatabaseDependent extends CRUDKubernetesDependentResource<Deployment, TodoApp> {

    @Override protected Deployment desired(TodoApp primary, Context<TodoApp> context) {
            return new DeploymentBuilder()
                .withNewMetadata()
                .withName(primary.getMetadata().getName() + "-db")
                ...
                .withNewSpec()
                .addNewContainer()
                .withName("postgres")
                .withImage("postgres:18")
                .addNewPort().withContainerPort(5432).endPort()
                .endContainer()
                .endSpec()
                .endTemplate()
                .endSpec()
                .build();
    }
}
