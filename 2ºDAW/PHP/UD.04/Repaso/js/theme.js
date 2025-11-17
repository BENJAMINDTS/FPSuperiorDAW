// Gestión de temas y preferencias
class ThemeManager {
    constructor() {
        this.init();
    }

    init() {
        this.applySavedTheme();
        this.setupEventListeners();
        this.setupThemeToggle();
    }

    applySavedTheme() {
        const preferences = this.getPreferences();
        this.setTheme(preferences.theme);
        this.setLanguage(preferences.language);
    }

    getPreferences() {
        try {
            // Leer de cookies
            const cookie = document.cookie
                .split('; ')
                .find(row => row.startsWith('user_preferences='));
            
            if (cookie) {
                const cookieValue = decodeURIComponent(cookie.split('=')[1]);
                const preferences = JSON.parse(cookieValue);
                return {
                    theme: preferences.theme || 'light',
                    language: preferences.language || 'es'
                };
            }
        } catch (e) {
            console.error('Error loading preferences:', e);
        }
        
        return {
            theme: 'light',
            language: 'es'
        };
    }

    setTheme(theme) {
        document.documentElement.setAttribute('data-theme', theme);
        
        // Actualizar el selector en el formulario
        const themeSelect = document.getElementById('theme');
        if (themeSelect) {
            themeSelect.value = theme;
        }
        
        // Guardar en localStorage para consistencia
        localStorage.setItem('theme', theme);
    }

    setLanguage(language) {
        document.documentElement.lang = language;
        
        // Actualizar el selector en el formulario
        const languageSelect = document.getElementById('language');
        if (languageSelect) {
            languageSelect.value = language;
        }
        
        // Guardar en localStorage para consistencia
        localStorage.setItem('language', language);
    }

    setupEventListeners() {
        // Escuchar cambios en el formulario de preferencias
        const preferenceForm = document.getElementById('preference-form');
        if (preferenceForm) {
            preferenceForm.addEventListener('submit', (e) => {
                this.handlePreferenceSubmit(e);
            });

            // Aplicar cambios inmediatamente al cambiar selects
            const themeSelect = document.getElementById('theme');
            const languageSelect = document.getElementById('language');

            if (themeSelect) {
                themeSelect.addEventListener('change', (e) => {
                    this.setTheme(e.target.value);
                    this.savePreferences();
                });
            }

            if (languageSelect) {
                languageSelect.addEventListener('change', (e) => {
                    this.setLanguage(e.target.value);
                    this.savePreferences();
                });
            }
        }

        // Toggle rápido de tema (Ctrl+T)
        document.addEventListener('keydown', (e) => {
            if (e.ctrlKey && e.key === 't') {
                e.preventDefault();
                this.toggleTheme();
            }
        });
    }

    setupThemeToggle() {
        // Crear toggle flotante si no existe
        if (!document.getElementById('theme-toggle')) {
            const toggle = document.createElement('button');
            toggle.id = 'theme-toggle';
            toggle.innerHTML = '🌙';
            toggle.title = 'Cambiar tema (Ctrl+T)';
            toggle.style.cssText = `
                position: fixed;
                bottom: 20px;
                right: 20px;
                width: 50px;
                height: 50px;
                border-radius: 50%;
                background: var(--primary-color);
                color: white;
                border: none;
                font-size: 1.5rem;
                cursor: pointer;
                z-index: 1000;
                box-shadow: 0 4px 15px rgba(0,0,0,0.2);
                transition: all 0.3s ease;
            `;
            
            toggle.addEventListener('click', () => this.toggleTheme());
            toggle.addEventListener('mouseenter', () => {
                toggle.style.transform = 'scale(1.1)';
            });
            toggle.addEventListener('mouseleave', () => {
                toggle.style.transform = 'scale(1)';
            });
            
            document.body.appendChild(toggle);
        }
    }

    handlePreferenceSubmit(e) {
        // Prevenir envío normal del formulario
        e.preventDefault();
        
        const form = e.target;
        const formData = new FormData(form);
        const theme = formData.get('theme');
        const language = formData.get('language');
        
        // Aplicar cambios inmediatamente
        this.setTheme(theme);
        this.setLanguage(language);
        this.savePreferences();
        
        // Mostrar feedback
        this.showSubmitFeedback(form);
        
        // Enviar formulario después de un breve delay
        setTimeout(() => {
            form.submit();
        }, 1000);
    }

    showSubmitFeedback(form) {
        const submitBtn = form.querySelector('button[type="submit"]');
        if (submitBtn) {
            const originalText = submitBtn.textContent;
            submitBtn.textContent = '✅ Guardado!';
            submitBtn.style.background = 'var(--success-color)';
            submitBtn.disabled = true;
            
            setTimeout(() => {
                submitBtn.textContent = originalText;
                submitBtn.style.background = '';
                submitBtn.disabled = false;
            }, 1000);
        }
    }

    savePreferences() {
        const theme = document.documentElement.getAttribute('data-theme') || 'light';
        const language = document.documentElement.lang || 'es';
        
        // Enviar al servidor via AJAX
        this.updateServerPreferences(theme, language);
    }

    async updateServerPreferences(theme, language) {
        try {
            const formData = new FormData();
            formData.append('theme', theme);
            formData.append('language', language);
            formData.append('csrf_token', document.querySelector('[name="csrf_token"]')?.value || '');

            const response = await fetch('../actions/update_preferences.php', {
                method: 'POST',
                body: formData
            });

            if (!response.ok) {
                throw new Error('Error del servidor');
            }
        } catch (error) {
            console.error('Error updating preferences:', error);
        }
    }

    toggleTheme() {
        const currentTheme = document.documentElement.getAttribute('data-theme');
        const newTheme = currentTheme === 'dark' ? 'light' : 'dark';
        
        this.setTheme(newTheme);
        this.savePreferences();
        
        // Actualizar ícono del toggle
        const toggle = document.getElementById('theme-toggle');
        if (toggle) {
            toggle.innerHTML = newTheme === 'dark' ? '🌞' : '🌙';
        }
    }
}

// Funciones de utilidad
const Utils = {
    showNotification: (message, type = 'info') => {
        const notification = document.createElement('div');
        notification.className = `notification notification-${type}`;
        notification.textContent = message;
        notification.style.cssText = `
            position: fixed;
            top: 20px;
            right: 20px;
            background: ${type === 'success' ? 'var(--success-color)' : 
                        type === 'error' ? 'var(--error-color)' : 
                        'var(--primary-color)'};
            color: white;
            padding: 1rem 1.5rem;
            border-radius: 8px;
            box-shadow: var(--shadow-lg);
            z-index: 1001;
            animation: slideInRight 0.3s ease;
        `;
        
        document.body.appendChild(notification);
        
        setTimeout(() => {
            notification.style.animation = 'slideOutRight 0.3s ease';
            setTimeout(() => notification.remove(), 300);
        }, 3000);
    }
};

// Inicializar cuando el DOM esté listo
document.addEventListener('DOMContentLoaded', () => {
    new ThemeManager();
    
    // Añadir estilos para las animaciones de notificación
    if (!document.getElementById('notification-styles')) {
        const styles = document.createElement('style');
        styles.id = 'notification-styles';
        styles.textContent = `
            @keyframes slideInRight {
                from {
                    opacity: 0;
                    transform: translateX(100%);
                }
                to {
                    opacity: 1;
                    transform: translateX(0);
                }
            }
            
            @keyframes slideOutRight {
                from {
                    opacity: 1;
                    transform: translateX(0);
                }
                to {
                    opacity: 0;
                    transform: translateX(100%);
                }
            }
        `;
        document.head.appendChild(styles);
    }
});