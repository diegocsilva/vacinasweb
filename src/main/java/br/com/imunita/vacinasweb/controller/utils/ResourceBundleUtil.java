package br.com.imunita.vacinasweb.controller.utils;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Classe utilitária para manipular mensagem de bundle. Contém todas as regras para localizar,
 * manipular e validar mensagens de bundle da aplicação. Esta classe está de acordo com a
 * recomendação de nomenclatura usada nos projetos web.
 */
public class ResourceBundleUtil {

    /**
     * Percorre todos os arquivos de bundle cadastrados na aplicação. Atualmente está fixado o
     * "ApplicationMessages" e o "/br/com/azi/component/gui/presentation/resources/messages/Message"
     *
     * @param locale Localidade corrente. s
     * @param key Chave a ser procurada.
     * @return Retorna o valor da chave ou "", caso não encontrado.
     */
    public static String getKey(Locale locale, String key, Object... params) {
        String[] files = {"ApplicationMessages"};
        String label = key;
        for (String bundleName : files) {
            ResourceBundle bundle = ResourceBundle.getBundle(bundleName, locale, getClassLoader());
            label = getParameterizedMessage(bundle.getString(key), locale, params);
            if (label != "") {
                return label;
            }
        }
        return label;
    }

    private static String getParameterizedMessage(String messageText, Locale messageLocale, Object[] params) {

        MessageFormat messageFormat = new MessageFormat(messageText, messageLocale);
        messageText = messageFormat.format(params, new StringBuffer(), null).toString();
        return messageText;
    }

    /**
     * Retorna a chave de bundle informando localidade, nome do arquivo de bundle e a própria chave.
     *
     * @param locale Localidade corrente.
     * @param bundleName Nome do arquivo de bundle sem a extensão e a sigla da lingua.
     * @param key Chave a ser procurada.
     * @return Retorna o valor da chave ou "", caso não encontrado.
     */
    public static String getKeyBundle(Locale locale, String bundleName, String key) {
        ResourceBundle bundle = ResourceBundle.getBundle(bundleName, locale, getClassLoader());
        try {
            return bundle.getString(key);
        } catch (MissingResourceException e) {
            // Não faz nada, pois queremos que não dê exceção quando não encontra a chave
            return key;
        }
    }

    /**
     * Verifica se a chave é um valor literal ou de fato uma chave de bundle. Para que uma texto
     * seja considerado uma chave de bundle, ele deve seguir a convenção de nomenclatura adotada que
     * define que as chaves de bundle devem: <li>Começar sempre com letra minúscula</li> <li>Não
     * deve conter espaços em branco</li> <li>A separação das palavras são feitas por ponto</li>
     * Poranto, se o texto passado não passar por estas regras, então ele não é considerado uma key
     * de bundle válida.
     *
     * @param value Key de bundle que será testada.
     * @return Retorna se é uma key válida ou não.
     */
    public static boolean isValidKey(String value) {
        String regex = "^[a-z]+(\\.[a-z][a-zA-Z0-9]*)+(\\.[a-z][a-zA-Z0-9]*)*";
        return value.matches(regex);
    }

    /**
     * Monta uma chave de bundle específica para exceções. Usa a recomendação do guia de
     * nomenclatura web para definir este tipo de chave.
     *
     * @param keybase Chave base da exceção. Normalmente, se passa somente o "<subsystem>.<page>".
     * @param e Exceção sobre a qual será montado a chave.
     * @return Retorna o chave de bundle referente a exceção passada.
     */
    public static String assembleExceptionKey(String keybase, Exception e) {
        return keybase + ".exception." + e.getClass().getSimpleName();
    }

    /**
     * Recupera o class loader da thread corrente.
     *
     * @return Class loader corrente.
     */
    private static ClassLoader getClassLoader() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        if (classLoader == null) {
            return ResourceBundleUtil.class.getClassLoader();
        }
        return classLoader;
    }
}
