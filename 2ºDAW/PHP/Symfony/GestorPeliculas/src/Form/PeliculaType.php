<?php

namespace App\Form;

use App\Entity\Pelicula;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\IntegerType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Validator\Constraints\Length;
use Symfony\Component\Validator\Constraints\NotBlank;
use Symfony\Component\Validator\Constraints\Range;

/**
 * PeliculaType
 *
 * Clase de formulario para la entidad Pelicula.
 * Define los campos del formulario, sus tipos y las restricciones de validación.
 *
 * @author     Benjamín
 * @package    App\Form
 */
class PeliculaType extends AbstractType
{
    /**
     * Construye el formulario con los campos de la entidad Pelicula.
     *
     * @param FormBuilderInterface $builder Constructor del formulario
     * @param array                $options Opciones adicionales del formulario
     */
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('titulo', TextType::class, [
                'label'       => 'Título',
                'constraints' => [
                    new NotBlank(['message' => 'El título no puede estar vacío.']),
                    new Length(['max' => 255, 'maxMessage' => 'El título no puede superar los 255 caracteres.']),
                ],
            ])
            ->add('director', TextType::class, [
                'label'       => 'Director',
                'constraints' => [
                    new NotBlank(['message' => 'El director no puede estar vacío.']),
                    new Length(['max' => 255]),
                ],
            ])
            ->add('anio', IntegerType::class, [
                'label'       => 'Año',
                'constraints' => [
                    new NotBlank(['message' => 'El año no puede estar vacío.']),
                    new Range([
                        'min'               => 1888,
                        'max'               => 2100,
                        'notInRangeMessage' => 'El año debe estar entre 1888 y 2100.',
                    ]),
                ],
            ])
            ->add('guardar', SubmitType::class, [
                'label' => $options['boton_label'],
            ])
        ;
    }

    /**
     * Configura las opciones del formulario.
     * Vincula el formulario a la entidad Pelicula y define opciones personalizadas.
     *
     * @param OptionsResolver $resolver Resolutor de opciones
     */
    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class'   => Pelicula::class,
            // Opción personalizada para cambiar el texto del botón submit
            'boton_label'  => 'Guardar',
        ]);
    }
}
