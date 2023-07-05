# Bridging Workflow Automation Tools with EMF Modeling Ecosystems

This section describes a motivating example and proposes a modeling ecosystem built with Eclipse Epsilon to implement the proposed scenario.

## Motivating Example

<p align="justify">
The departments of a University have international collaborations with professors and researchers that can be invited to visit the hosting research group for various reasons, e.g., for giving a course or for invited visiting positions. A university faculty member can open an “invited guest position” on a system where he/she must express related information. This info can be related to the guest position, period of staying, title of collaboration, hours of lectures, and course title. The remuneration can be calculated according to the department's rules and period of staying and collaboration. After this information has been set up, an invitation letter must be generated and possibly sent to the invitee to specify the economic treatment and all the info that can be useful and used by the invitee to motivate his/her visit. 
As long as we specify the title of the course, the dates, and an abstract, a web page can be generated and published online to announce the course or the novel collaboration. Concerning the payment, when a new guest is invited, a new payment must be emitted, and this should be managed by another system when the collaboration  has been completed and confirmed.
</p>

## The proposed modeling ecosystem
<p align="justify">
In the following, we present the implemented model management operations to support the described scenario, implemented with a modeling ecosystem.
In our case study, we use Eclipse modeling framework (EMF)\footnote{\url{https://www.eclipse.org/modeling/emf/}} as IDE and Epsilon\footnote{\url{https://www.eclipse.org/epsilon/}} as the framework to implement the model-to-model transformation expressed with the ETL language, model-to-code, and model-to-text transformations using EGL language. We used EVL\footnote{\url{https://www.eclipse.org/epsilon/doc/evl/}} for validating the models at different stages and EOL\footnote{\url{https://www.eclipse.org/epsilon/doc/eol/}} as a scripting language for interacting with external services. ANT is used for concatenating the various services.
</p>

### Guest Invitation metamodel with an instance model

<img src="https://github.com/xxyyzzaa/QAandMetricsForArch/blob/main/assets/7.png" width="300" height="250">
