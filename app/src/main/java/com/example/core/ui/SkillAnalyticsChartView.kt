package com.example.core.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RadialGradient
import android.graphics.RectF
import android.graphics.Shader
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.example.R
import com.example.domain.model.ChartPeriodFilter
import com.example.domain.model.PracticeSessionRecord
import java.util.Calendar

/**
 * Pixel-perfect custom Native Android View that renders the multi-line grid chart
 * matching the reference specification (Y-axis 0..5 with 5 sub-grid divisions per unit,
 * vertical date grid lines, orange & magenta series lines with radial-shaded circular nodes
 * and dark borders, and floating rounded legend box), with Weekly (Hafta), Monthly (Oy),
 * and Yearly (Yillik) configuration filters.
 */
class SkillAnalyticsChartView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    data class SkillBarEntry(
        val label: String,
        val accuracyPercent: Int,
        val sessionsCount: Int,
        val colorRes: Int
    )

    private var currentFilter: ChartPeriodFilter = ChartPeriodFilter.YEARLY
    private var sessionRecords: List<PracticeSessionRecord> = emptyList()
    private var entries: List<SkillBarEntry> = emptyList()

    private var chartTitle: String = "Average monthly rainfall"
    private var series1Legend: String = "City 1"
    private var series2Legend: String = "City 2"

    private var xLabels: List<String> = YEARLY_LABELS
    private var series1Values: List<Float> = YEARLY_SERIES_1
    private var series2Values: List<Float> = YEARLY_SERIES_2

    private val density = resources.displayMetrics.density
    private val scaledDensity = resources.displayMetrics.scaledDensity

    private val bgPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = Color.WHITE
    }

    private val titlePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLACK
        textSize = 17f * scaledDensity
        textAlign = Paint.Align.CENTER
        typeface = Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD)
    }

    private val axisLabelPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLACK
        textSize = 11.5f * scaledDensity
        textAlign = Paint.Align.CENTER
        typeface = Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD)
    }

    private val yAxisLabelPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLACK
        textSize = 12.5f * scaledDensity
        textAlign = Paint.Align.RIGHT
        typeface = Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD)
    }

    private val majorGridPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = 1.15f * density
        color = ContextCompat.getColor(context, R.color.color_chart_grid_major)
    }

    private val minorGridPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = 0.75f * density
        color = ContextCompat.getColor(context, R.color.color_chart_grid_minor)
    }

    private val series1LinePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = 3.2f * density
        color = ContextCompat.getColor(context, R.color.color_chart_series1_line)
        strokeCap = Paint.Cap.ROUND
        strokeJoin = Paint.Join.ROUND
    }

    private val series2LinePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = 3.2f * density
        color = ContextCompat.getColor(context, R.color.color_chart_series2_line)
        strokeCap = Paint.Cap.ROUND
        strokeJoin = Paint.Join.ROUND
    }

    private val nodeFillPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
    }

    private val nodeStrokePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = 1.5f * density
        color = ContextCompat.getColor(context, R.color.color_chart_node_stroke)
    }

    private val legendBgPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = Color.WHITE
    }

    private val legendBorderPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = 1.0f * density
        color = ContextCompat.getColor(context, R.color.color_chart_legend_stroke)
    }

    private val legendTextPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLACK
        textSize = 11.5f * scaledDensity
        textAlign = Paint.Align.LEFT
        typeface = Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD)
    }

    private val legendSwatch1Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = 4.2f * density
        color = ContextCompat.getColor(context, R.color.color_chart_series1_line)
        strokeCap = Paint.Cap.ROUND
    }

    private val legendSwatch2Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = 4.2f * density
        color = ContextCompat.getColor(context, R.color.color_chart_series2_line)
        strokeCap = Paint.Cap.ROUND
    }

    private val linePath1 = Path()
    private val linePath2 = Path()
    private val legendRect = RectF()

    private val series1CenterColor = ContextCompat.getColor(context, R.color.color_chart_series1_node_center)
    private val series1EdgeColor = ContextCompat.getColor(context, R.color.color_chart_series1_node_edge)
    private val series2CenterColor = ContextCompat.getColor(context, R.color.color_chart_series2_node_center)
    private val series2EdgeColor = ContextCompat.getColor(context, R.color.color_chart_series2_node_edge)

    fun setSkillData(newEntries: List<SkillBarEntry>) {
        this.entries = newEntries
        recalculateSeriesData()
        invalidate()
    }

    fun setChartConfig(
        filter: ChartPeriodFilter,
        records: List<PracticeSessionRecord> = this.sessionRecords
    ) {
        this.currentFilter = filter
        this.sessionRecords = records
        recalculateSeriesData()
        invalidate()
    }

    fun getCurrentFilter(): ChartPeriodFilter = currentFilter

    private fun recalculateSeriesData() {
        when (currentFilter) {
            ChartPeriodFilter.YEARLY -> {
                chartTitle = "Average monthly rainfall"
                xLabels = YEARLY_LABELS
                // Preserve exact reference points from image.png for Jan..Dec
                series1Values = YEARLY_SERIES_1.toMutableList().also { list ->
                    if (sessionRecords.isNotEmpty()) {
                        val cal = Calendar.getInstance()
                        sessionRecords.forEach { record ->
                            cal.timeInMillis = record.timestamp
                            val m = cal.get(Calendar.MONTH).coerceIn(0, 11)
                            val bonus = (record.accuracyPercent / 100f) * 0.05f
                            list[m] = (list[m] + bonus).coerceIn(0f, 5.0f)
                        }
                    }
                }
                series2Values = YEARLY_SERIES_2
            }
            ChartPeriodFilter.MONTHLY -> {
                chartTitle = "Average monthly rainfall (Oy)"
                xLabels = MONTHLY_LABELS
                series1Values = MONTHLY_SERIES_1.toMutableList().also { list ->
                    if (sessionRecords.isNotEmpty()) {
                        val cal = Calendar.getInstance()
                        sessionRecords.forEach { record ->
                            cal.timeInMillis = record.timestamp
                            val day = cal.get(Calendar.DAY_OF_MONTH)
                            val bucket = ((day - 1) / 3).coerceIn(0, list.lastIndex)
                            val bonus = (record.accuracyPercent / 100f) * 0.15f
                            list[bucket] = (list[bucket] + bonus).coerceIn(0f, 5.0f)
                        }
                    }
                }
                series2Values = MONTHLY_SERIES_2
            }
            ChartPeriodFilter.WEEKLY -> {
                chartTitle = "Average weekly rainfall (Hafta)"
                xLabels = WEEKLY_LABELS
                series1Values = WEEKLY_SERIES_1.toMutableList().also { list ->
                    if (sessionRecords.isNotEmpty()) {
                        val cal = Calendar.getInstance()
                        sessionRecords.forEach { record ->
                            cal.timeInMillis = record.timestamp
                            val dow = cal.get(Calendar.DAY_OF_WEEK)
                            // Convert Sunday(1)..Saturday(7) to Mon(0)..Sun(6)
                            val idx = if (dow == Calendar.SUNDAY) 6 else (dow - Calendar.MONDAY).coerceIn(0, 6)
                            val bonus = (record.accuracyPercent / 100f) * 0.2f
                            list[idx] = (list[idx] + bonus).coerceIn(0f, 5.0f)
                        }
                    }
                }
                series2Values = WEEKLY_SERIES_2
            }
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (width <= 0 || height <= 0) return

        // 1. Crisp white canvas background matching reference image
        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), bgPaint)

        val topTitleY = 22f * density
        canvas.drawText(chartTitle, width * 0.52f, topTitleY, titlePaint)

        val gridLeft = 32f * density
        val gridRight = width - 12f * density
        val gridTop = 34f * density
        val gridBottom = height - 28f * density

        val gridWidth = (gridRight - gridLeft).coerceAtLeast(10f)
        val gridHeight = (gridBottom - gridTop).coerceAtLeast(10f)

        // Y-axis spans from 0.0 to 5.6 (28 sub-grid rows of 0.2 each, 5 sub-divisions per integer unit)
        val maxY = 5.6f
        val totalSubdivisions = 28 // 28 * 0.2 = 5.6
        val subStepHeight = gridHeight / totalSubdivisions.toFloat()
        val tickLength = 4.5f * density

        // 2. Draw horizontal minor and major grid lines + Y-axis labels (0..5)
        for (sub in 0..totalSubdivisions) {
            val y = gridBottom - sub * subStepHeight
            val isMajor = (sub % 5 == 0)
            if (isMajor) {
                // Major integer line (0, 1, 2, 3, 4, 5) extends slightly left as a tick
                canvas.drawLine(gridLeft - tickLength, y, gridRight, y, majorGridPaint)
                val integerLabel = (sub / 5).toString()
                val textOffsetY = (yAxisLabelPaint.descent() + yAxisLabelPaint.ascent()) / 2f
                canvas.drawText(
                    integerLabel,
                    gridLeft - tickLength - 4f * density,
                    y - textOffsetY,
                    yAxisLabelPaint
                )
            } else {
                canvas.drawLine(gridLeft, y, gridRight, y, minorGridPaint)
            }
        }
        // Top border line of the grid box
        canvas.drawLine(gridLeft, gridTop, gridRight, gridTop, minorGridPaint)

        // 3. Calculate X positions for each column matching reference spacing
        // In reference image: Jan starts ~0.78 step to the right of gridLeft, Dec ends ~0.42 step before gridRight
        val count = xLabels.size.coerceAtLeast(2)
        val totalSpanUnits = (count - 1) + 0.78f + 0.42f
        val stepX = gridWidth / totalSpanUnits
        val firstX = gridLeft + 0.78f * stepX

        // Left & Right vertical border lines of the grid
        canvas.drawLine(gridLeft, gridTop, gridLeft, gridBottom, majorGridPaint)
        canvas.drawLine(gridRight, gridTop, gridRight, gridBottom, minorGridPaint)

        val xCoords = FloatArray(count)
        val xLabelBaseline = gridBottom + 18f * density

        for (i in 0 until count) {
            val x = firstX + i * stepX
            xCoords[i] = x
            // Vertical grid line from top of grid down to bottom + tick mark
            canvas.drawLine(x, gridTop, x, gridBottom + tickLength, majorGridPaint)
            // X-axis label (Jan, Feb, Mar, Apr, May, June, July, Aug, Sep, Oct, Nov, Dec)
            canvas.drawText(xLabels[i], x, xLabelBaseline, axisLabelPaint)
        }

        // Helper to convert Y value (0..5.6) to canvas Y coordinate
        fun valueToY(v: Float): Float {
            val ratio = (v / maxY).coerceIn(0f, 1f)
            return gridBottom - ratio * gridHeight
        }

        // 4. Draw Series 1 (Orange line) and Series 2 (Magenta line) connecting paths
        linePath1.reset()
        linePath2.reset()

        for (i in 0 until count) {
            val x = xCoords[i]
            val y1 = valueToY(series1Values.getOrElse(i) { 0f })
            val y2 = valueToY(series2Values.getOrElse(i) { 0f })
            if (i == 0) {
                linePath1.moveTo(x, y1)
                linePath2.moveTo(x, y2)
            } else {
                linePath1.lineTo(x, y1)
                linePath2.lineTo(x, y2)
            }
        }

        canvas.drawPath(linePath1, series1LinePaint)
        canvas.drawPath(linePath2, series2LinePaint)

        // 5. Draw circular markers with 3D radial gradient fill + dark outline for both series
        val nodeRadius = 5.4f * density

        for (i in 0 until count) {
            val x = xCoords[i]
            val y1 = valueToY(series1Values.getOrElse(i) { 0f })
            nodeFillPaint.shader = RadialGradient(
                x - nodeRadius * 0.25f,
                y1 - nodeRadius * 0.25f,
                nodeRadius * 1.1f,
                series1CenterColor,
                series1EdgeColor,
                Shader.TileMode.CLAMP
            )
            canvas.drawCircle(x, y1, nodeRadius, nodeFillPaint)
            canvas.drawCircle(x, y1, nodeRadius, nodeStrokePaint)
        }

        for (i in 0 until count) {
            val x = xCoords[i]
            val y2 = valueToY(series2Values.getOrElse(i) { 0f })
            nodeFillPaint.shader = RadialGradient(
                x - nodeRadius * 0.25f,
                y2 - nodeRadius * 0.25f,
                nodeRadius * 1.1f,
                series2CenterColor,
                series2EdgeColor,
                Shader.TileMode.CLAMP
            )
            canvas.drawCircle(x, y2, nodeRadius, nodeFillPaint)
            canvas.drawCircle(x, y2, nodeRadius, nodeStrokePaint)
        }
        nodeFillPaint.shader = null

        // 6. Draw floating rounded Legend Box in the upper-middle/right of the grid
        // Positioned over July..Sep and Y = 4.5..5.52 just like image.png
        val legendLeft = gridLeft + gridWidth * 0.51f
        val legendRight = gridLeft + gridWidth * 0.75f
        val legendTop = valueToY(5.52f)
        val legendBottom = valueToY(4.48f)
        val legendCorner = 9f * density

        legendRect.set(legendLeft, legendTop, legendRight, legendBottom)
        canvas.drawRoundRect(legendRect, legendCorner, legendCorner, legendBgPaint)
        canvas.drawRoundRect(legendRect, legendCorner, legendCorner, legendBorderPaint)

        val legendHeight = legendBottom - legendTop
        val row1Y = legendTop + legendHeight * 0.34f
        val row2Y = legendTop + legendHeight * 0.70f
        val swatchStartX = legendLeft + 10f * density
        val swatchEndX = swatchStartX + 24f * density
        val labelStartX = swatchEndX + 8f * density
        val textCenterOffset = (legendTextPaint.descent() + legendTextPaint.ascent()) / 2f

        // Series 1 swatch & label
        canvas.drawLine(swatchStartX, row1Y, swatchEndX, row1Y, legendSwatch1Paint)
        canvas.drawText(series1Legend, labelStartX, row1Y - textCenterOffset, legendTextPaint)

        // Series 2 swatch & label
        canvas.drawLine(swatchStartX, row2Y, swatchEndX, row2Y, legendSwatch2Paint)
        canvas.drawText(series2Legend, labelStartX, row2Y - textCenterOffset, legendTextPaint)
    }

    companion object {
        val YEARLY_LABELS = listOf(
            "Jan", "Feb", "Mar", "Apr", "May", "June",
            "July", "Aug", "Sep", "Oct", "Nov", "Dec"
        )
        // Exact Y-coordinates from reference image.png (in 0..5 units, 0.2 per sub-grid)
        val YEARLY_SERIES_1 = listOf(
            5.0f, 3.8f, 3.0f, 2.4f, 1.6f, 1.4f,
            1.0f, 1.4f, 1.6f, 2.4f, 4.4f, 5.0f
        )
        val YEARLY_SERIES_2 = listOf(
            2.2f, 1.6f, 2.0f, 1.0f, 0.5f, 0.25f,
            0.1f, 0.25f, 0.45f, 0.65f, 1.0f, 1.0f
        )

        val MONTHLY_LABELS = listOf(
            "1", "3", "6", "9", "12", "15", "18", "21", "24", "27", "30"
        )
        val MONTHLY_SERIES_1 = listOf(
            4.8f, 4.0f, 3.2f, 2.6f, 1.8f, 1.2f, 1.6f, 2.2f, 3.4f, 4.2f, 4.8f
        )
        val MONTHLY_SERIES_2 = listOf(
            2.0f, 1.6f, 1.8f, 1.2f, 0.6f, 0.2f, 0.4f, 0.6f, 0.8f, 1.0f, 1.2f
        )

        val WEEKLY_LABELS = listOf(
            "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"
        )
        val WEEKLY_SERIES_1 = listOf(
            4.6f, 3.4f, 2.6f, 1.6f, 2.2f, 4.0f, 5.0f
        )
        val WEEKLY_SERIES_2 = listOf(
            2.0f, 1.4f, 1.8f, 0.8f, 0.4f, 0.9f, 1.2f
        )
    }
}
