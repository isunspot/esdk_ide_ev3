package com.huawei.ecosystem.interaction.preference;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.huawei.ecosystem.interaction.Activator;
import com.huawei.ecosystem.interaction.i18n.Messages;

/**
 * <h6>交互首选项</h6>
 */
public class InteractionPreferencePage extends FieldEditorPreferencePage
        implements IWorkbenchPreferencePage
{
    /**
     * 构造函数 
     */
    public InteractionPreferencePage()
    {
        super();
        setPreferenceStore(Activator.getDefault().getPreferenceStore());
    }
    
    /* （此注释不是Javadoc注释）
     * @see org.eclipse.jface.preference.FieldEditorPreferencePage#createFieldEditors()
     */
    @Override
    protected void createFieldEditors()
    {
        Composite parent = getFieldEditorParent();
        parent.setLayout(new GridLayout(1, false));
        
        Composite general = new Composite(parent, SWT.NONE);
        general.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        general.setLayout(new GridLayout(2, false));
        
        StringFieldEditor ipField = new StringFieldEditor(
                InteractionPreferenceInitializer.SERVER_HOST,
                Messages.InteractionPreferencePage_Server_IP, general);
        ipField.setEmptyStringAllowed(false);
        ipField.fillIntoGrid(general, 2);
        addField(ipField);
        StringFieldEditor portField = new StringFieldEditor(
                InteractionPreferenceInitializer.SERVER_PORT,
                Messages.InteractionPreferencePage_Server_Port, general);
        portField.setEmptyStringAllowed(false);
        portField.fillIntoGrid(general, 2);
        addField(portField);
        IntegerFieldEditor timeoutField = new IntegerFieldEditor(
                InteractionPreferenceInitializer.LOCATION,
                Messages.InteractionPreferencePage_Location, general);
        timeoutField.setValidRange(0, Integer.MAX_VALUE);
        timeoutField.fillIntoGrid(general, 2);
        addField(timeoutField);
        
        //        IntegerFieldEditor timeoutField = new IntegerFieldEditor(
        //                InteractionPreferenceInitializer.TIME_OUT, Messages.InteractionPreferencePage_Timeout, general);
        //        timeoutField.setValidRange(0, Integer.MAX_VALUE);
        //        timeoutField.fillIntoGrid(general, 2);
        //        addField(timeoutField);
        //        
        //        StringFieldEditor successField = new StringFieldEditor(
        //                InteractionPreferenceInitializer.SUCC_MARK, Messages.InteractionPreferencePage_Succ_Mark,
        //                general);
        //        successField.setEmptyStringAllowed(false);
        //        successField.fillIntoGrid(general, 2);
        //        addField(successField);
        //        
        Group group = new Group(parent, SWT.NONE);
        GridData data = new GridData(GridData.FILL_HORIZONTAL);
        group.setLayoutData(data);
        group.setLayout(new GridLayout(2, false));
        group.setText(Messages.InteractionPreferencePage_Services);
        StringFieldEditor serviceSuccField = new StringFieldEditor(
                InteractionPreferenceInitializer.SUCCESS_SERVICE,
                Messages.InteractionPreferencePage_Service_Succ, group);
        serviceSuccField.setEmptyStringAllowed(false);
        serviceSuccField.fillIntoGrid(group, 2);
        addField(serviceSuccField);
        
        StringFieldEditor serviceFailField = new StringFieldEditor(
                InteractionPreferenceInitializer.FAIL_SERVICE,
                Messages.InteractionPreferencePage_Service_Fail, group);
        serviceFailField.setEmptyStringAllowed(false);
        serviceFailField.fillIntoGrid(group, 2);
        addField(serviceFailField);
        //        
        //        StringFieldEditor serviceTimeoutField = new StringFieldEditor(
        //                InteractionPreferenceInitializer.TIMEOUT_SERVICE,
        //                Messages.InteractionPreferencePage_Service_Timeout, group);
        //        serviceTimeoutField.setEmptyStringAllowed(false);
        //        serviceTimeoutField.fillIntoGrid(group, 2);
        //        addField(serviceTimeoutField);
    }
    
    /* （此注释不是Javadoc注释）
     * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
     */
    @Override
    public void init(IWorkbench workbench)
    {
        
    }
}
