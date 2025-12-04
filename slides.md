---
colorSchema: light
routerMode: hash
layout: image
theme: neversink
neversink_slug: KCD Suisse Rommand 2025
transition: slide-left
image: images/theme_front.png
class: custom-bgr
hideInToc: true
mdc: true
favicon: https://raw.githubusercontent.com/jenkinsci/oss-symbols-api-plugin/refs/heads/main/src/main/resources/images/symbols/operatorframework-icon-color.svg
---

# Making on-prem infrastructure feel Cloud-Native with the Java Operator SDK

**Valentin Delaye**
<Email v="valentin.delaye@elca.ch" color="white" />

---
layout: side-title
side: l
color: slate-light
titlewidth: is-3
align: rm-lm
title: whoami
hideInToc: true
---

:: title ::

<p class="text-right">
  <img
    src="/images/me.png"
    alt="Valentin Delaye"
    class="rounded-lg shadow-lg mb-4 inline-block"
    style="width: 150px; height: 150px; object-fit: cover;"
  />
</p>

<p class="text-sm text-opacity-70">
  <mdi-github /> jonesbusy<br>
  <mdi-linkedin /> valentin.delaye<br>
</p>

:: content ::

- <MdiBadgeAccountOutline /> `Valentin Delaye`
- <SimpleIconsBackstage /> `Platform Engineer at ELCA`
- <SimpleIconsJenkins /> `Jenkins maintainer and Governance Board`
- <OrasLogo /> `Java SDK maintainer`
- <GsocLogo /> `Google Summer of Code Mentor (2024 & 2025)`

---
layout: image-right
color: slate-light
image: images/agenda.png
class: text
---

# Agenda

- Developer intent (`What`) vs implementation (`How`)
- `K8S Operators` and the `KRM` model
- `Java Operator SDK` and it's `Quarkus` extension
- Examples with K8S internal and external resources
- Q&A

---
layout: top-title-two-cols
color: slate-light
columns: is-6
align: l-lt-lt
---

<v-click>
<AdmonitionType type='tip' >
Developers only define intent in manifests. Platform handles the rest.
</AdmonitionType>
</v-click>

:: title ::

# Deploying app on K8S

:: left ::

## What?

<v-click>- Connect my app to a database</v-click><br />
<v-click>- I need persistent storage for my service</v-click><br />
<v-click>- I need my app to be highly available</v-click><br />
<v-click>- ...</v-click><br />

:: right ::

## How?

<v-click>- How to deploy a database? Where?</v-click><br />
<v-click>- Which storage class to use and how to provision it?</v-click><br />
<v-click>- How many replicas?</v-click><br />
<v-click>- Cluster topology?</v-click><br />
<v-click>- Affinity rules?</v-click><br />
<v-click>- ...</v-click><br />

---
layout: image-right
color: slate-light
image: images/helm-boat.png
class: text
---

# Helm to the rescue?

- Package your Kubernetes resources as single deployable unit
- Hide complexity of multiple manifests
- Define values to customize your deployments per environment
- Integrate with GitOps tools (Flux CD, Argo CD, etc.)

---
layout: top-title-two-cols
color: slate-light
columns: is-6
align: l-lt-lt
---


:: title ::

# Helm release

:: left ::

<v-click>
<<< @/snippets/cr/helm-release-dev.yaml
</v-click>

<v-click at="3">
<AdmonitionType type='warning' >
Different values for different environments (dev/prod)<br />
Default values inside your charts<br />
More abstraction needed
</AdmonitionType>
</v-click>

:: right ::

<v-click>
<<< @/snippets/cr/helm-release-prod.yaml
</v-click>

---
layout: top-title-two-cols
color: slate-light
columns: is-6
align: l-lt-lt
---
<v-click>

</v-click>

:: title ::

# Custom Resource

:: left ::

<<< @/snippets/cr/cr-app-dev.yaml

<v-click>

- Deploy a database on K8S <strong>or</strong> provision a new schema on an external one

</v-click>

<v-click>

- Deploy a `Secret` with required connection info

</v-click>

<v-click>

- Deploy the helm release and pass required values
</v-click>

:: right ::

<<< @/snippets/cr/cr-app-prod.yaml

<v-click>
<img src="/images/custom_resource_dependent.excalidraw.svg" alt="CR dep"/>
</v-click>

---
layout: top-title-two-cols
color: slate-light
---

:: title ::

# KRM and developer platform

:: left ::

<v-click>
Fit well for a self-service developer platform
</v-click>


<v-click>
Everything is a resource that user can request (UI, CLI, GitOps, ...)
</v-click>

<v-click>

- An application
- A database (inside or outside K8S)
- A firewall rule
- A VM
- A DNS entry
- A service account token or LDAP group
- etc.

</v-click>

:: right ::

<v-click>

<img src="/images/idp.excalidraw.svg" alt="IDP"/>

</v-click>

---
layout: top-title
color: slate-light
---

:: title ::

# Java Operator SDK

:: content ::

- CNCF incubating project to build Kubernetes Operators (Go and Java)
- Some operators like Keycloak Operator use the Java implementation
- Quarkus extension + Native image support

---
layout: top-title-two-cols
color: slate-light
columns: is-6
align: l-lt-lt
---

:: title ::

# A custom resource

:: left ::

<<< @/snippets/java/TodoApp.java

<AdmonitionType type='note' >
Fabric8 CRDGenerator to the rescue!
</AdmonitionType>

:: right ::

<<< @/snippets/crds/todoapps.samples.elca.ch-v1.yml

---
layout: top-title
color: slate-light
---

:: title ::

# K8S dependent database resource

:: content ::

<<< @/snippets/java/KubernetesDatabaseDependent.java {*}{lines:true,class:'!children:text-xs'}


---
layout: top-title
color: slate-light
---

:: title ::

# External dependent database resource (1)

:: content ::

<<< @/snippets/java/ExternalDatabaseDependent.java {*}{lines:true,class:'!children:text-xs'}

---
layout: top-title
color: slate-light
---

:: title ::

# External dependent database resource (2)

:: content ::

<<< @/snippets/java/DatabaseResource.java {*}{lines:true,class:'!children:text-xs'}

<AdmonitionType type='warning' >
<ul>
  <li>Equality if not using records</li>
</ul>
</AdmonitionType>

---
layout: top-title
color: slate-light
---

:: title ::

# Dependent resource (4)

:: content ::

<<< @/snippets/java/TodoAppReconciler.java {*}{lines:true,class:'!children:text-xs'}

---
layout: top-title
color: slate-light
--- 

:: title ::

# What we learned (1)

:: content ::

- Use `conditions` in `status` to reflect the progress of reconciliation (even for simple resources)

```yaml
status:
  conditions:
  - lastTransitionTime: "2025-10-07T11:49:44.177461113Z"
    message: Deployed TodoApp
    observedGeneration: 1
    status: "True"
    type: Done
  - status: "False"
    type: Started
  - status: "False"
    type: HasErrors
  deployedAppVersion: 0.0.11
  deployedVersion: 0.16.1
  lastHandledReconcileAt: "2025-10-07T11:49:44.192938122Z"
  location: my-todo-app.apps.my-domain.elca.ch
  observedGeneration: 1
```

---
layout: top-title
color: slate-light
--- 

:: title ::

# What we learned (2)

:: content ::

- Monitor closely external resource polling intervals (equality, outbound HTTP requests, etc.)

<img src="/images/outbound-requests.png" width="900px" alt="SonarQube" class="mx-auto" />

- Use events to inform users of important state changes

<img src="/images/events.png" width="900px" alt="SonarQube" class="mx-auto" />

---
layout: top-title
color: slate-light
--- 

:: title ::

# What we learned (3)

:: content ::

- Test your controllers!

<img src="/images/sonarqube-tests.png" width="700px" alt="SonarQube" class="mx-auto" />

- Additional columns are useful to give instant feedback to consumers

```
kubectl get todoapps.samples.elca.ch

NAME          STATUS             DONE   APP VERSION   VERSION   LOCATION
my-todo-app   Deployed TodoApp   True   0.0.11        0.16.1    my-todo-app.apps.my-domain.elca.ch

```

---
layout: top-title
color: slate-light
--- 

:: title ::

# Challenges we faced

:: content ::

## Technical

- Learning curve
- Many best practices to follow to implement a good operator
- Expertise in Kubernetes internals
- Migrate existing workflows/automation to custom resources

## Non-technical

- Culture change and mindset for (Dev vs Ops)
- Non-cloud-native apps and legacy systems
- Wire into existing infrastructure and processes

---
layout: top-title
color: slate-light
--- 

:: title ::

# Current state

:: content ::

<v-click>

- 6 months in production (migrate some traditional automation to KRM model)
- ~ `8000` CR managed (and growing!)
- ~ `15` minutes to check all CRs state with max 5 reconciliations in parallel (restart)
- ~ Less that `half second` start time with Quarkus native image
- ~ `512 MB` memory for the operator

</v-click>

<v-click>
<img src="/images/success_piccard.png" width="250px" alt="Success Picard" class="mx-auto" />
</v-click>

---
layout: image-right
color: slate-light
image: images/question.jpg
class: text
---

# Thank you!

<p class="text-sm text-opacity-70">
  <mdi-email />valentin.delaye@elca.ch <br>
  <mdi-linkedin /><a href="https://www.linkedin.com/in/valentin.delaye" class="ns-c-iconlink">valentin.delaye</a><br>
  <mdi-github /><a href="https://github.com/jonesbusy" class="ns-c-iconlink">jonesbusy</a><br>
</p>

# References

- [Java Operator SDK Documentation](https://javaoperatorsdk.io/docs/)
- [API conditions and conventions](https://github.com/kubernetes/community/blob/master/contributors/devel/sig-architecture/api-conventions.md#spec-and-status)
- [🚀 KRM-Native GitOps: Yes — Without Flux, No. (FluxCD or Nothing.)](https://www.linkedin.com/pulse/krm-native-gitops-yes-without-flux-fluxcd-nothing-mialon-wsmue/)
