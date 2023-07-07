# Model Management operations in MDE

Using a motivating example, we illustrate model management operations in Model Driven Engineering (MDE).

## Motivating Example

<p align="justify">
The departments of a University have international collaborations with professors and researchers that can be invited to visit the hosting research group for various reasons, e.g., for giving a course or for invited visiting positions. A university faculty member can open an “invited guest position” on a system where he/she must express related information. This info can be related to the guest position, period of staying, title of collaboration, hours of lectures, and course title. The remuneration can be calculated according to the department's rules and period of staying and collaboration. After this information has been set up, an invitation letter must be generated and possibly sent to the invitee to specify the economic treatment and all the info that can be useful and used by the invitee to motivate his/her visit. 
As long as we specify the title of the course, the dates, and an abstract, a web page can be generated and published online to announce the course or the novel collaboration. Concerning the payment, when a new guest is invited, a new payment must be emitted, and this should be managed by another system when the collaboration  has been completed and confirmed.
</p>

## The proposed modeling ecosystem
<p align="justify">
In the following, we present the implemented model management operations to support the described scenario, implemented with a modeling ecosystem. In our case study, we use Eclipse modeling framework <a href="https://www.eclipse.org/modeling/emf/">(EMF)</a> as IDE and <a href="https://www.eclipse.org/epsilon/">Epsilon</a> as the framework to implement the model-to-model transformation expressed with the <a href="https://www.eclipse.org/epsilon/doc/etl/">ETL</a> language, model-to-code, and model-to-text transformations using <a href="https://eclipse.dev/epsilon/doc/egl/">EGL</a> language. We used <a href="https://www.eclipse.org/epsilon/doc/evl/">EVL</a> for validating the models at different stages and  <a href="https://www.eclipse.org/epsilon/doc/eol/">EOL</a> as a scripting language for interacting with external services. ANT is used for concatenating the various services.
</p>

<p align="justify">
In order to fully support the case study specification, we would need to implement a model transformation from the  <b><i>Guest Invitation</i></b> to the <b><i>PersonRemuneration</i></b> metamodel (we do not report the rest of the toolchain from this metamodel for lack of space), a code generator generating web pages for the course/collaboration, a document generator for generating the invitation letter, a script invoking an external Java service for sending emails and finally all the operations must be supported by validation scripts, assuring that the model instances are correct. In the following, we describe each technology used in the modeling ecosystem to support the case study by referring to its purpose and reporting the snippet of code/models developed. 
</p>

### Guest Invitation metamodel with an instance model
<p align="justify">The following figure shows the metamodel engineered starting from the domain specification and an exemplary instance regarding the Guest Invitation process of a University
</p>
<img src="https://github.com/gssi/emf_workflow_project/blob/main/figures/university_organization_metamodel.png" >
<p align="center"> Fig.1. Guest Invitation metamodel with an instance model</p>

### PersonRemuneration Target Metamodel with a model instance
<p>
  In the following <b><i>PersonRemuneration Metamodel</i></b>, all the concepts for the system managing the payments are included
</p>

<img src="https://github.com/gssi/emf_workflow_project/blob/main/figures/person_remuneration_metamodel.png" >
<p align="center"> Fig.2. PersonRemuneration Target Metamodel with a model instance</p>

### 1. Model Transformation
<p align="justify">
Model transformations are usually defined with languages and tools allowing to transform a model into another. Transformations can be <b>endogenous</b> or <b>exogenous</b>, depending on the source and target metamodels. In EMF, ATL (Atlas Transformation Language), QVT (Query/View/Transformation), VIATRA (M2M), Epsilon Transformation Language <a href="https://www.eclipse.org/epsilon/doc/etl/">(ETL)</a>, Epsilon Object Language <a href="https://www.eclipse.org/epsilon/doc/eol/">(EOL)</a> are some of the tools that can be used to implement a transformation. ATL, ETL, and VIATRA are declarative rule-based model transformation languages in which transformational mappings are expressed via rules, where the modeler can set source and target metamodel elements. 
These transformation languages express rules with text-based syntaxes, whereas others use graphical editors to offer an intuitive way of representing rules.
</p>
<img src="https://github.com/gssi/emf_workflow_project/blob/main/figures/etl_guest2remuneration.png" >
<p align="center"> Fig.3. ETL Model to Model Transformation</p>
<p align="justify">
In this figure, the ETL code for the model-to-model transformation generating an instance of the <b><i>Person Remuneration</i></b> metamodel starting from the <b><i>Guest Invitation</i></b> metamodel.
</p>

### 2. Model Validation
<p align="justify">
Data validation within an execution flow provides benefits such as timely error identification, result reliability, prevention of costly errors, improved performance, data security, and enhanced user experience. When it comes to the execution flow, incorporating data validation can bring a wide range of advantages. For instance, it can help identify errors in a timely manner which could help prevent costly mistakes down the line. Additionally, it can help ensure the reliability of results, which is critical for many applications or to prevent unexpected behavior. By validating data, the system can ensure that the data it is working with is clean and well-structured. Furthermore, incorporating data validation can enhance the user experience, as it helps ensure the application is intuitive and user-friendly.
Data validity checks can be performed in the EMF environment using the Epsilon Validation Language (EVL). Fig.4. shows how two validation rules have been defined in the context of the <b><i>Remuneration</i></b> metaclass (line 1), which  verifies if the total amount to be remunerated is positive  (lines 2-5) and if the application status is in the <b><i>ReadyToBeSent</i></b> state (lines 7-11). This inhibits the possibility of working remuneration automatically with negative amounts or when the process is not finalized.
</p>
<img src="https://github.com/gssi/emf_workflow_project/blob/main/figures/evl_validation.png" >
<p align="center"> Fig.4. ETL Model to Model Transformation</p>

### 3. Code generation
<p align="justify">
Template-based code generation (TBCG) is a code synthesis technique generating code from templates, which are high-level specifications mixing static parts and dynamic parts that will be filled with variables actualized from models. In the MDE ecosystems, some examples of languages and tools can be used as <a href="https://www.eclipse.org/acceleo/">Acceleo</a>, EGL, or <a href="http://wiki.eclipse.org/Xpand">AXPand</a>.
To obtain the same result in an EMF environment, we use Epsilon's EGL transformation language. It makes use of EGX, which is an EGL template coordination language.
</p>
<img src="https://github.com/gssi/emf_workflow_project/blob/main/figures/egl_transformation_1.png" >
<p align="center"> Fig.5. Code generation in EMF (HTML)</p>

### 4. Document generation
<p align="justify">
Document generation is an automation task that can be traced back to code generation since transformations and code generation, in particular, can generate any string or format. Indeed, code generation is also called <b>{Model to Text</b> transformations. In the MDE ecosystems, we can implement document generation in multiple ways and using multiple languages and tools. Usually, EMF Acceleo or EGL can, as in the previous point, generate documents exactly as we have generated the source code. What changes, in this case, is the format of the generated artifact that it must conform to the technical space.
</p>
<img src="https://github.com/gssi/emf_workflow_project/blob/main/figures/egl_transformation_2.png" >
<p align="center"> Fig.6. EMF Document Generation</p>

### 5. Interaction with external services
<p align="justify">
To automate the sending of emails to the guest, EOL allows the creation of objects of the underlying programming environment using native types.  For instance,  the EOL excerpt in Fig.7.  uses an external service to send an email via Java. Lines 3 and 4 refer to the Java class used for sending emails.
This class provides a basic configuration for SMTP host, port, etc. More details can be seen at the following <a href="https://tinyurl.com/3mjmw5sn">link</a>. 
This Java class requires the insertion of a username and a password to start sending emails. Once set, the <b><i>sendMail</i></b> method (line 6) builds the email by inserting the recipient's email address and defining the mail's subject and message body, which is pre-built in the same way as seen in Fig.6. Implementation details can also be seen at this <a href="https://tinyurl.com/3u84frah">address</a>.
</p>
<img src="https://github.com/gssi/emf_workflow_project/blob/main/figures/EOL_mail_service.png" >
<p align="center"> Fig.7. EOL - Calling External Service for Sending Emails</p>

### 6. Workflow automation in Epsilon
<p align="justify">
When all these artifacts have been developed, model management activities must be combined together to form the entire workflow. The Epsilon framework provides a set of Apache <a href="https://ant.apache.org/">ANT</a> tasks for assembling multi-step automated build processes. An excerpt of the ANT script is reported in Fig.8.
</p>
<img src="https://github.com/gssi/emf_workflow_project/blob/main/figures/ANT_workflow.png" height=500 width=800>
<p align="center"> Fig.8. Excerpt of workflow automation with ANT</p>

<p align="justify">
Specifically, from line 4 to line 8, the loading of the input model is expected, where some options must be set, such as the <b><i>name</i></b> (line 5), the actual <b><i>modelfile</i></b> (line 6), and the URI of the metamodel to which the model conforms (line 7). Finally, in line 8, it is defined that the input model must read-only <b><i>read="true"</i></b> and must not be modified <b><i>store="false"</i></b>.
In line 11, the model to be provided as output in the model-to-model transformation of lines 22-26 is also prepared; in this case, the read and write options are <b><i>read="false"</i></b> and <b><i>store="true"</i></b> as the model must be created from scratch.
The workflow, therefore, involves the validation of the input model. In line 17 the same validation file reported in Fig.4 is used.
Then, the model-to-model transformation is carried out in lines 22-26, with the same transformation seen in Fig.3.
In line 29, through the model-to-code transformation, the invitation email is generated (as seen in Fig.5), and finally, through the use of EOL, the external Java service that sends the emails with the input model information is called.
</p>

