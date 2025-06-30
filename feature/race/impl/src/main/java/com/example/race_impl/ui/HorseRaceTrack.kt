package com.example.race_impl.ui

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.utils.R as UtilsR

/**
 * Компонент для отображения одной беговой дорожки с анимированной лошадью
 * 
 * @param horseName Имя лошади, отображаемое слева от дорожки
 * @param progress Прогресс движения лошади от 0f до 1f
 * @param finishPosition Позиция лошади на финише (null если гонка не завершена)
 */
@Composable
internal fun HorseRaceTrack(
    horseName: String,
    progress: Float,
    finishPosition: Int? = null
) {
    // Создаем бесконечную анимацию для движений лошади
    val infiniteTransition = rememberInfiniteTransition(label = "horse_animation")
    
    // Сохраняем ширину дорожки для правильного расчета движения
    var trackWidth by remember { mutableStateOf(0) }
    
    // Получаем плотность экрана для конвертации dp в пиксели
    val density = LocalDensity.current
    
    // Создаем анимацию вертикального покачивания лошади
    // Лошадь двигается вверх-вниз на 5 пикселей в каждую сторону
    val bounceAnimation by infiniteTransition.animateFloat(
        initialValue = -5f,
        targetValue = 5f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,  // Продолжительность одного цикла анимации
                easing = FastOutSlowInEasing  // Плавное ускорение и замедление
            ),
            repeatMode = RepeatMode.Reverse  // Анимация идет туда-обратно
        ),
        label = "bounce"
    )

    // Создаем плавную анимацию горизонтального движения лошади
    // При изменении progress лошадь плавно перемещается в новую позицию
    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(
            durationMillis = 300,  // Время перемещения до новой позиции
            easing = LinearEasing  // Равномерное движение
        ),
        label = "progress"
    )

    // Основной контейнер для дорожки
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        // Верхняя строка с именем лошади и позицией на финише
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Имя лошади
            Text(
                text = horseName,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
            
            // Отображаем медаль и место, если лошадь финишировала
            if (finishPosition != null) {
                Text(
                    text = when(finishPosition) {
                        1 -> "🥇 1-е место"
                        2 -> "🥈 2-е место"
                        3 -> "🥉 3-е место"
                        else -> "$finishPosition-е место"
                    },
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(4.dp))

        // Беговая дорожка с анимированной лошадью
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(36.dp)
                .onSizeChanged { trackWidth = it.width }  // Запоминаем ширину дорожки
                .clip(RoundedCornerShape(12.dp))  // Скругленные углы
                .background(Color.White)  // Белый фон
        ) {
            // Красная линия по центру дорожки
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color.Red)
            )

            // Анимированная лошадь
            val horseSize = 32.dp  // Размер иконки лошади
            val horseSizePx = with(density) { horseSize.toPx() }  // Размер в пикселях
            val maxTravel = trackWidth - horseSizePx  // Максимальное расстояние для движения

            // Иконка лошади с анимацией
            Icon(
                painter = painterResource(id = UtilsR.drawable.horse),
                contentDescription = "Horse",
                modifier = Modifier
                    .size(horseSize)
                    .graphicsLayer {
                        translationX = animatedProgress * maxTravel  // Горизонтальное движение
                        translationY = bounceAnimation  // Вертикальное покачивание
                        rotationY = if (animatedProgress >= 1f) 180f else 0f  // Разворот на финише
                    }
                    .align(Alignment.CenterStart),
                // Цвет лошади в зависимости от места на финише
                tint = when {
                    finishPosition == 1 -> Color(0xFFFFD700) // Золото
                    finishPosition == 2 -> Color(0xFFC0C0C0) // Серебро
                    finishPosition == 3 -> Color(0xFFCD7F32) // Бронза
                    else -> MaterialTheme.colorScheme.primary // Обычный цвет
                }
            )
        }
    }
} 