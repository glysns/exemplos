package digytal.java.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;



public class ExcelResultSet {
	/**
	 * Map das Colunas com indice e label das colunas
	 */
	private Map<String, Integer> layout;
	/**
	 * Map das Linhas label das colunas e valor
	 */
	private Map<String, Object> rowData;
	/**
	 * Formatador de Datas para valores do tipo java.util.Data
	 */
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	/**
	 * Formatador de Datas para valores do tipo java.time.LocalDate
	 */
	private DateTimeFormatter localDateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	/**
	 * Formatador de Datas para valores do tipo java.time.LocalDateTime
	 */
	private DateTimeFormatter localDateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	/**
	 * Formatador de Numero para valores do tipo Numericos como Double, BigDecimal e etc
	 */
	private NumberFormat numberFormat = new DecimalFormat ("R$ #,##0.00", new DecimalFormatSymbols (new Locale ("pt", "BR")));
	
	
	/**
	 * Planilha selecionada
	 */
	private Sheet sheet;
	
	/**
	 * Cursor da linha atual
	 */
	private int currentRow = 1;
	
	/**
	 * Construção da classe para ler arquivos .xls ou .xlsx
	 * @param File file: Arquivo .xls ou .xlsx
	 * @param int sheetIndex: Indice da planilha, padrão = 0
	 * @param int labelIndex: Indice do cabeçalho, padrão = 0
	 */
	
	public ExcelResultSet(File file ) throws Exception {
		this(file,0,0);
		
	}
	/**
	 * Construção da classe para ler arquivos .xls ou .xlsx
	 * @param File file: Arquivo .xls ou .xlsx
	 * @param int sheetIndex: Indice da planilha, padrão = 0
	 * @param int labelIndex: Indice do cabeçalho, padrão = 0
	 */
	public ExcelResultSet(File file, int sheetIndex,int labelIndex ) throws Exception {
		this(new FileInputStream(file),sheetIndex, labelIndex);
		
	}
	/**
	 * Construção da classe para ler arquivos .xls ou .xlsx
	 * @param InputStream inputStream: Stream de Arquivo .xls ou .xlsx
	 * @param int sheetIndex: Indice da planilha, padrão = 0
	 * @param int labelIndex: Indice do cabeçalho, padrão = 0
	 */
	
	public ExcelResultSet(InputStream inputStream) throws Exception {
		this(inputStream,0,0);
	}
	/**
	 * Construção da classe para ler arquivos .xls ou .xlsx
	 * @param InputStream inputStream: Stream de Arquivo .xls ou .xlsx
	 * @param int sheetIndex: Indice da planilha, padrão = 0
	 * @param int labelIndex: Indice do cabeçalho, padrão = 0
	 */
	public ExcelResultSet(InputStream inputStream, int sheetIndex, int labelIndex) throws Exception {
		Workbook workbook = WorkbookFactory.create(inputStream);
		this.sheet = workbook.getSheetAt(sheetIndex);
		initLayout(labelIndex);
		
	}
	/**
	 * Define um novo formata para campos do tipo Date
	 * @param format
	 */
	public void setDateFormat(String format) {
		dateFormat = new SimpleDateFormat(format);
	}
	
	/**
	 * Define um novo formata para campos do tipo LocalDate
	 * @param format
	 */
	public void setLocalDateFormat(String format) {
		localDateFormatter = DateTimeFormatter.ofPattern(format);
	}
	
	/**
	 * Define um novo formata para campos do tipo LocalDateTime
	 * @param format
	 */
	public void setLocalDateTimeFormat(String format) {
		localDateTimeFormatter = DateTimeFormatter.ofPattern(format);
	}
	
	private void initLayout(int labelIndex) {
		layout = new HashMap<String, Integer>();
		Row row = sheet.getRow(labelIndex);
		Iterator<Cell> cellIterator = row.cellIterator();
		int col = 0;
		while (cellIterator.hasNext()) {
			Cell cell = cellIterator.next();
			String label = cell.getStringCellValue();
			layout.put(label, col++);
		}
	}
	/**
	 * Retorna a lista dos campos do cabeçalho
	 * @return
	 */
	public Set<String> getHeader(){
		return layout.keySet();
	}
	/**
	 * Intera sobre a planilha obtendo os valores de cada linha
	 * @return
	 */
	public boolean next() {
		String content = sheet.getRow(currentRow).getCell(0).getStringCellValue();
		if(content== null || content.isEmpty())
			return false;
		
		if (currentRow == (sheet.getLastRowNum() + 1))
			return false;
		
		readRow();
		return true;
	}
	private void readRow() {
		rowData = new HashMap<String, Object>();
		Row row = sheet.getRow(currentRow++);
		layout.forEach((k, v) -> {
			Cell cell = row.getCell(v);
			readColls(k, cell);
		});
		
	}
	private void readColls(String header, Cell cell) {
		Object value = readColls(cell);
		rowData.put(header, value);
	}

	private Object readColls(Cell cell) {
		if(cell==null || cell.getCellTypeEnum()==CellType._NONE)
			return null;
		
		Object value = null;
		CellType type = cell.getCellTypeEnum() == CellType.FORMULA ? cell.getCachedFormulaResultTypeEnum(): cell.getCellTypeEnum();
		switch (type) {
		case BOOLEAN:
			value = cell.getBooleanCellValue();
			break;
		case STRING:
			value = cell.getRichStringCellValue().getString();
			break;
		case NUMERIC:
			if (DateUtil.isCellDateFormatted(cell)) {
				value = cell.getDateCellValue();
			} else {
				value = cell.getNumericCellValue();
			}
			break;
		case BLANK:
			value = "";
			break;
		}
		return value;
	}
	
	
	/**
	 * Returna um Date pelo pelo fiel label da coluna
	 * @param field
	 * @return
	 * @throws ParseException
	 */
	public Date getDate(String field) throws ParseException {
		Object date = get(field);
		if (isNullOrEmpty(date))
			return null;

		if (date instanceof String)
			return dateFormat.parse(date.toString());
		else
			return (Date) date;
	}
	/**
	 * Returna um LocalDate pelo pelo fiel label da coluna
	 * @param field
	 * @return
	 * @throws ParseException
	 */
	public LocalDate getLocalDate(String field) {
		Object date = get(field);
		if (isNullOrEmpty(date))
			return null;

		if (date instanceof String)
			return LocalDate.parse(date.toString(), localDateFormatter);
		else {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime((Date) date);
			LocalDate localDate = LocalDateTime.ofInstant(calendar.toInstant(), calendar.getTimeZone().toZoneId())
					.toLocalDate();
			return localDate;
		}
	}
	/**
	 * Returna um LocalDateTime pelo pelo fiel label da coluna
	 * @param field
	 * @return
	 * @throws ParseException
	 */
	public LocalDateTime getLocalDateTime(String field) {
		Object date = get(field);
		if (isNullOrEmpty(date))
			return null;

		if (date instanceof String)
			return LocalDateTime.parse(date.toString(), localDateTimeFormatter);
		else {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime((Date) date);
			LocalDateTime localDateTime = LocalDateTime.ofInstant(calendar.toInstant(),
					calendar.getTimeZone().toZoneId());
			return localDateTime;
		}
	}
	/**
	 * Returna um Boolean pelo pelo fiel label da coluna e a expressão que determina o valor true
	 * @param field
	 * @return
	 * @throws ParseException
	 */
	public Boolean getBoolean(String field, String trueValue) {
		Object value = get(field);
		if (isNullOrEmpty(value))
			return null;

		if (value instanceof Double) {
			value = ((Double) value).intValue();
		}
		return value.toString().equals(trueValue) ? true : false;
	}
	/**
	 * Returna um BigDecimal pelo pelo fiel label da coluna
	 * @param field
	 * @return
	 * @throws ParseException
	 */
	public BigDecimal getBigDecimal(String field) {
		Double value = getDouble(field);
		if (isNullOrEmpty(value))
			return null;

		return new BigDecimal(value);
	}
	/**
	 * Returna um Double pelo pelo fiel label da coluna
	 * @param field
	 * @return
	 * @throws ParseException
	 */
	public Double getDouble(String field) {
		Object value = get(field);
		if (isNullOrEmpty(value))
			return null;
		if(value instanceof Double)
			return (Double) value;
		else {
			try {
				value = numberFormat.parse(value.toString()).doubleValue();
				return (Double) value;
			} catch (ParseException e) {
				return 0.0d;
			}
			
		}
	}
	/**
	 * Returna uma String pelo pelo fiel label da coluna
	 * @param field
	 * @return
	 * @throws ParseException
	 */
	public String getString(String field) {
		Object value = get(field);
		if (isNullOrEmpty(value))
			return null;
		if(value instanceof Double)
			return Long.valueOf(((Double)value).longValue()).toString();
		else
			return value.toString();
	}
	/**
	 * Returna um Integer pelo pelo fiel label da coluna
	 * @param field
	 * @return
	 * @throws ParseException
	 */
	public Integer getInteger(String field) {
		Object value = get(field);
		if (isNullOrEmpty(value))
			return null;
		
		return Double.valueOf(value.toString()).intValue();
	}

	private boolean isNullOrEmpty(Object value) {
		return value == null || value.toString().isEmpty();
	}
	/**
	 * Returna um Generic pelo pelo fiel label da coluna
	 * @param field
	 * @return
	 * @throws ParseException
	 */
	public <T> T get(String field) {
		final Object value = rowData.get(field);
		return (T) value;

	}
}
