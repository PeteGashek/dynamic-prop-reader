package proploader;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

import java.util.HashMap;
import java.util.Properties;


/**
 * Created by ghost on 3/25/18.
 */
public class DynamicPropertiesFileReaderTask implements BeanFactoryAware {

    private static Logger logger = Logger.getLogger(DynamicPropertiesFileReaderTask.class);
    private Properties coreDynamicPropertiesBean;
    private HashMap<String, String> dynamicPropertiesMap;
    private BeanFactory beanFactory;

    /**
     * Starts reading the dynamic properties
     *
     */
    public void start() {
        setCoreDynamicPropertiesBean(createCoreDynamicPropertiesBeanInstance());
        logger.info("**** Dynamic Properties File Reader Task is being started... ****");
        readConfiguration();
        logger.info("**** Dynamic Properties File Reader Task is stopped... ****");
    }

    /**
     * Reads all the dynamic properties
     *
     */
    private void readConfiguration() {
        readSomeStringMsg();

    }

    /**
     * Reads Some_string_msg dynamic property
     *
     */
    private void readSomeStringMsg() {
        String messageContent = getCoreDynamicPropertiesBean().getProperty(SystemConstants.DYNAMIC_STR_MSG, SystemConstants.STRING_MSG_DEFAULT);

        if (messageContent.equals("")){getDynamicPropertiesMap().put(SystemConstants.DYNAMIC_STR_MSG, SystemConstants.STRING_MSG_DEFAULT);
            logger.error(SystemConstants.DYNAMIC_STR_MSG + " value is not found so its default value is set. Default value : " + SystemConstants.STRING_MSG_DEFAULT);

        } else { messageContent = messageContent.trim();getDynamicPropertiesMap().put(SystemConstants.DYNAMIC_STR_MSG, messageContent);
            logger.info(SystemConstants.DYNAMIC_STR_MSG + " : " + getDynamicPropertiesMap().get(SystemConstants.DYNAMIC_STR_MSG));
        }
    }


    /**
     * Gets CoreDynamicPropertiesBean
     *
     * @return Properties coreDynamicPropertiesBean
     */
    public Properties getCoreDynamicPropertiesBean() {
        return coreDynamicPropertiesBean;
    }

    /**
     * Sets CoreDynamicPropertiesBean
     *
     * @param Properties coreDynamicPropertiesBean
     */
    public void setCoreDynamicPropertiesBean(Properties coreDynamicPropertiesBean) {
        this.coreDynamicPropertiesBean = coreDynamicPropertiesBean;
    }

    /**
     * Gets DynamicPropertiesMap
     *
     * @return HashMap dynamicPropertiesMap
     */
    public HashMap<String, String> getDynamicPropertiesMap() {
        return dynamicPropertiesMap;
    }

    /**
     * Sets DynamicPropertiesMap
     *
     * @param HashMap dynamicPropertiesMap
     */
    public void setDynamicPropertiesMap(HashMap<String, String> dynamicPropertiesMap) {
        this.dynamicPropertiesMap = dynamicPropertiesMap;
    }

    /**
     * Gets a new instance of CoreDynamicPropertiesBean
     *
     * @return Properties CoreDynamicPropertiesBean
     */
    public Properties createCoreDynamicPropertiesBeanInstance() {
        return  (Properties) this.beanFactory.getBean(SystemConstants.BEAN_NAME_CORE_DYNAMIC_PROPERTIES_BEAN);
    }

    /**
     * Sets BeanFactory
     *
     * @param BeanFactory beanFactory
     */
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

}