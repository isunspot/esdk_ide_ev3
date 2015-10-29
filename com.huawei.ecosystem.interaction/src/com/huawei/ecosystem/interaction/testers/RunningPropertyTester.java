//package com.huawei.ecosystem.interaction.testers;
//
//import org.eclipse.core.expressions.PropertyTester;
//
//import com.huawei.ecosystem.interaction.Interaction;
//
///**
// * <h6>判断交互是否在运行</h6>
// */
//public class RunningPropertyTester extends PropertyTester
//{
//    
//    /* （此注释不是Javadoc注释）
//     * @see org.eclipse.core.expressions.IPropertyTester#test(java.lang.Object, java.lang.String, java.lang.Object[], java.lang.Object)
//     */
//    @Override
//    public boolean test(Object receiver, String property, Object[] args,
//            Object expectedValue)
//    {
//        if ("running".equals(property)) //$NON-NLS-1$
//        {
//            return null == expectedValue ? Interaction.isRunning()
//                    : Boolean.valueOf(String.valueOf(expectedValue)) == Interaction.isRunning();
//        }
//        return false;
//    }
//    
//}
